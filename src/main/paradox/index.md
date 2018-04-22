@@@section { data-background="#15a9ce" }

### Scala: Beyond the basics

##### Christopher Batey 
##### @chbatey 

@@@

@@@section

![Akka logo](images/akka_full_color.svg)

Open Source toolkit for building Concurrent, Distributed, Resilient Message-Driven applications on the JVM

@notes[Akka grew out of the realization, circa 2009, that threads are a heavyweight abstraction that is hard to make resilient. Inspired by Erlang, asynchronous model, actor model, but not covering that today.]

@@@

@@@section

Agenda:

1. Programming Language basics
1. Concurrency
1. Function composition vs dependencies
1. Error handling + higher kinded types

@@@@notes

What to expect

* Cats
* Dogs

@@@@

@@@

@@@section

Disclaimer: I do not have a PhD in type theory. If I misuse terms
I am sorry :(

@@@

@@@section

## 1.Programming Languages 

@@@@notes

Semantics vs syntax

@@@@

@@@


@@@section

## Semantics vs Syntax 

@@@

@@@section

### Expressions

What does this evaluate to in Java?

```java
if (x > 0) {
  true;
} else {
  false;
}
```

@@@@notes

Notes

@@@@

@@@

@@@section

### Expressions

@@snip[x]($root$/src/main/java/djava/expressions/ControlFlow.java){#if-mutable}

@@@@notes

Notes

@@@@

@@@

@@@section

### Expressions

@@snip[x]($root$/src/main/java/djava/expressions/ControlFlow.java){#if-uninit}

@@@@notes

Notes

@@@@

@@@

@@@section

### Expressions

What does this evaluate to in Scala?

@@snip[x]($root$/src/main/scala/expressions/ControlFlow.scala){#if-expression}

@@@@notes

Notes

@@@@

@@@

@@@section

@span[`Statements have side effects`]

@span[`Expressions evaluate to values`]{ .fragment }

@@@

@@@secction

### Expressions

@@snip[x]($root$/src/main/scala/expressions/ControlFlow.scala){#if-assign}

@@@@notes

Notes

@@@@

@@@

@@@section

### Expressions

@@snip[x]($root$/src/main/scala/expressions/ControlFlow.scala){#if-short}

@@@@notes

Notes

@@@@

@@@

@@@section

## Immutability

@@@

@@@section

## Immutability

* Object orientation. Abstraction over data, provide behaviour.
* Functional. Immutable data. Abstract over functions.

@@@

@@@section

## Referential transparency

@@snip[x]($root$/src/main/scala/expressions/ReferentialTransparency.scala){#x}
@@snip[x]($root$/src/main/scala/expressions/ReferentialTransparency.scala){#y .fragment}
@@snip[x]($root$/src/main/scala/expressions/ReferentialTransparency.scala){#x-good .fragment}
@@snip[x]($root$/src/main/scala/expressions/ReferentialTransparency.scala){#x-bad .fragment}

@@@@notes

* E.g hitting a database

@@@@

@@@

@@@section

* Example of OO

@@@

@@@section

* Example of functional 

@@@

@@@section

* Extreme example of parametric polymorphism 

@@@

@@@section

* The more abstract a function the fewer implementations there are
* Link to John De Goes

@@@




@@@section

* Something awesome about concurrency

@@@


@@@section

# Function composition

http://batey.info/fs-function-composition

@@@


@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#web-request}
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#data .fragment}
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#serialisation .fragment}
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#controller .fragment}

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #service }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #repo .fragment }

@@@

@@@section

## Do it yourself wiring

@@@@Notes

* You then feel obliged to test it

@@@@

@@@

@@@section

#### Three language features to avoid this:
* Function Composition 
* Currying
* Partial Application

@@@

@@@section

@@snip[x]($root$/src/main/java/djava/layers/LayersVsComposition.java){ #add }
@@snip[x]($root$/src/main/java/djava/layers/LayersVsComposition.java){ #add10-reuse .fragment }
@@snip[x]($root$/src/main/java/djava/layers/LayersVsComposition.java){ #multiply .fragment }
@@snip[x]($root$/src/main/java/djava/layers/LayersVsComposition.java){ #multiply10 .fragment }
@@snip[x]($root$/src/main/java/djava/layers/LayersVsComposition.java){ #composition .fragment }

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #add }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #add10 .fragment }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #add-curried .fragment }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #what-type .fragment }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #what-type2 .fragment }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #add10-curried .fragment }

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #composition-1 }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #composition-2 .fragment}
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #composition-3 .fragment}
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #composition-4 .fragment}

@@@@notes

Notes

@@@@

@@@

@@@section

## Currying

* Each function a single argument
* Enabled partial application

@@@

@@@section

## Partial application

* Provide a subset of the parameters to get a new function

@@@

@@@section

## Function composition

@span[`f: String -> Int`]
 
@span[`g: Int -> Banana`] { .fragment }
 
@span[`g` compose `f`] { .fragment }

@span[`f` andThen `g`] { .fragment }
 
@span[`String -> Banana`] { .fragment }

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#web-request}

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#data}

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#serialisation}

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#controller}

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #service }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #repo }

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#web-request}
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #de-serialize-func .fragment }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #customer-func .fragment }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #db-func .fragment }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #serialize-func .fragment }

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #full-request-function }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#web-request}

@@@@notes

* Func composition not always so easy
* More realistically we need DI as well e.g. where does out database connection come from?

@@@@

@@@

@@@section

## Currying + Partial application to the rescue

@@@@notes

* Monadic ways as well.
* Will link resources

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-pa-con }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-pa .fragment }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-pa-use .fragment }

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #full-request-function }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#web-request}

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-class }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-class-use .fragment }

@@@@notes

* Scala is a FP-OO hybrid

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-use .fragment }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-define .fragment }

@@@@notes

Implicits have to be explicitly in scope

@@@@

@@@

@@@section

## Section summary
* Techniques
    * Function Composition
    * Currying
    * Partial Application
* Benefits
    * Reduced need for mocking
    * Reduced need for a DI framework

@@@@notes

* Learning a language/compiler vs learning a reflection framework

@@@@

@@@

@@@section

# Higher kinded types

http://batey.info/???

@@@

@@@section

```java
String
int
Person
Customer
```

vs


```java
List<T>
Iterable<T>
```

@@@@notes

Notes

@@@@

@@@

@@@section

```java
List<T> :: T -> List<T>
String -> List<String>
```

List is a type level function takes a type and produces a concrete type

```java
Map<K, V>
```

Map takes two types and produces a concrete type

@@@@notes

* Java generics break this for compatibility

@@@@

@@@


@@@section

## Abstracting over higher kinded types

@@@@notes

* Abstract over the inner type e.g. the T in List<T>
* How about the list?

@@@@

@@@

@@@section

## Motivation

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #web-request .fragment }

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #web-request-2 .fragment }

@@@@notes

* Async
* Not using exceptions

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #web-request .fragment }
@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #serialize-func .fragment }
@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #customer-func .fragment }
@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #db-func .fragment }
@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #deserialise-create .fragment }


@@@@notes

* Async
* Not using exceptions

@@@@

@@@

@@@section

```scala
def flatMap(f: A => Future[B]): Future[B]
```

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #save .fragment }

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #full .fragment }

@@@@notes

* Async
* Not using exceptions

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #full2 }

@@@@notes

Notes

@@@@

@@@

@@@section

### Ready to do all this again with Either?

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #abstracted .fragment }
@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #mappable .fragment }

@@@@notes

Notes

@@@@

@@@

@@@section

### How useful is this?

* Ratpack Promise
* Hystrix: RxJava Observable
* Cassandra database driver: Guava Listenable Future
* Java8's Completable Future

@@@@notes

Notes

@@@@

@@@

@@@section

# Implicits

http://batey.info/???

@@@

@@@section

## Which implicit feature don't you like?

@@@

@@@section

## Implicits

@span[Many sensible uses of implicits replace reflection]
@span[Reflection is more implicit than implicits!!] { .fragment }

@@@

@@@section

## Implicit parameters

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-use .fragment }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-define .fragment }

@@@

@@@section

## Implicit type conversions

@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #classes }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #clean .fragment }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #ruby .fragment }

@@@

@@@section

TODO lots of pictures of ruby

@@@

@@@section

## Implicit type conversions

@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #classes }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #clean }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #ruby }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #cleaning .fragment }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #implicit .fragment }

@@@

@@@section

## Principled type conversions

@@@

@@@section


@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #mappable }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #future-mappable .fragment }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #cf-mappable .fragment }

@@@

@@@section

# Embedded DSLs

http://batey.info/???

@@@

@@@section

@@@section

## Happy hAkking!

Slides & Code
:  [github.com/chbatey/scala-basics](https://github.com/chbatey/scala-basics)

Akka
:  [akka.io](https://akka.io), [developer.lightbend.com/start](https://developer.lightbend.com/start)

Tweet
: [@chbatey](https://twitter.com/chbatey)


@@@

@span[$selectedLanguage$]{#selectedLanguage}

@@@vars
<script>
  const selectedLanguage = document.getElementById('selectedLanguage').innerHTML.toLowerCase()
  const hiddenLanguage = (selectedLanguage == "java") ? "scala" : "java"

  console.log(selectedLanguage)
  var javaFragments = document.getElementsByClassName('group-' + hiddenLanguage)
  while (javaFragments.length > 0) {
    javaFragments[0].remove()
  }
</script>
@@@