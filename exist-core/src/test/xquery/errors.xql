(:
 : eXist-db Open Source Native XML Database
 : Copyright (C) 2001 The eXist-db Authors
 :
 : info@exist-db.org
 : http://www.exist-db.org
 :
 : This library is free software; you can redistribute it and/or
 : modify it under the terms of the GNU Lesser General Public
 : License as published by the Free Software Foundation; either
 : version 2.1 of the License, or (at your option) any later version.
 :
 : This library is distributed in the hope that it will be useful,
 : but WITHOUT ANY WARRANTY; without even the implied warranty of
 : MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 : Lesser General Public License for more details.
 :
 : You should have received a copy of the GNU Lesser General Public
 : License along with this library; if not, write to the Free Software
 : Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 :)
xquery version "3.1";

(:~
 : Tests to check if the reported line numbers for errors are correct:
 :
 : * if a function is called dynamically. In previous eXist versions, the
 : line number always pointed to the place where the function item was created,
 : not the actual function body
 : * if newlines occur in string literals, constructors or comments
 :)
module namespace et="http://exist-db.org/xquery/test/error-test";

declare namespace test="http://exist-db.org/xquery/xqsuite";

(: Causes an error when called :)
declare function et:test($a) {
    $a + "bla"
};

(: May cause an error when called :)
declare function et:test2($a) {
    $a/foo
};

declare
    %test:assertEquals(38)
function et:dynamically-called-function() {
    let $fn := function-lookup(xs:QName("et:test"), 1)
    return
        try {
            $fn(1)
        } catch * {
            $err:line-number
        }
};

declare
    %test:assertEquals(43)
function et:dynamically-called-function-path-expr() {
    let $fn := function-lookup(xs:QName("et:test2"), 1)
    return
        try {
            $fn(1)
        } catch * {
            $err:line-number
        }
};

declare
    %test:assertEquals(74)
function et:inline-function-call() {
    let $fn := function($a) {
        $a + "bla"
    }
    return
        try {
            $fn(1)
        } catch * {
            $err:line-number
        }
};

declare
    %test:assertEquals(38)
function et:function-reference-call() {
    let $fn := et:test#1
    return
        try {
            $fn(1)
        } catch * {
            $err:line-number
        }
};

declare
    %test:assertEquals(108)
function et:nl-in-string-literal() {
    try {
        let $foo := "bar


        "
        return
            element x {
                attribute y { "1" },
                (: next line will generate dynamic error :)
                element z { sum($foo) }
            }
    } catch * {
        $err:line-number
    }
};

declare
    %test:assertEquals(127)
function et:nl-in-string-constructor() {
    try {
        let $foo := ``[bar


        ]``
        return
            element x {
                attribute y { "1" },
                (: next line will generate dynamic error :)
                element z { sum($foo) }
            }
    } catch * {
        $err:line-number
    }
};

declare
    %test:assertEquals(145)
function et:nl-in-element-constructor() {
    try {
        let $foo :=
            <test>
                <p>foo

                </p>
            </test>
        return
            "abc"/node()
    } catch * {
        $err:line-number
    }
};

declare
    %test:assertEquals(158)
function et:nl-in-comment() {
    try {
        (: This is a

        multiline comment :)
        "abc"/node()
    } catch * {
        $err:line-number
    }
};

declare 
    %test:assertXPath("/error[@line='2'][contains(., 'Invalid qname console:log')]")
function et:compile-query-unknown-func() {
    let $query := ``[
        console:log('foo')
    ]``
    return
        util:compile-query($query, "xmldb:exist://")
};

declare 
    %test:assertXPath("/self::info[@result='pass']")
function et:compile-query-variable-decl-pass() {
    let $query := ``[
        declare variable $v := map {
            "a": "b",
            "f": function() {
                ()
            }
        };
        $v?f()
    ]``
    return
        util:compile-query($query, "xmldb:exist://")
};

(: Should not result in an NPE, see https://github.com/eXist-db/exist/pull/1520#issuecomment-604514099 :)
declare 
    %test:assertXPath("/error[@line='5'][contains(., 'Invalid qname console:log')]")
function et:compile-query-variable-decl() {
    let $query := ``[
        declare variable $v := map {
            "a": "b",
            "f": function() {
                console:log('foo')
            }
        };
        $v?f()
    ]``
    return
        util:compile-query($query, "xmldb:exist://")
};

declare 
    %test:assertXPath("/error[@line='6'][contains(., 'local:test')]")
function et:compile-query-func-decl() {
    let $query := ``[
        declare function local:test($x) {
            $x
        };

        local:test(123, 345)
    ]``
    return
        util:compile-query($query, "xmldb:exist://")
};