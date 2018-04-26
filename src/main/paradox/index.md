@@@section { data-background="#15a9ce" }

### Scala: Beyond the basics

##### Christopher Batey 
##### @chbatey 

@@@

@@@section

![Akka logo](images/akka_full_color.svg)

Open Source toolkit for building Concurrent, Distributed, Resilient Message-Driven applications on the JVM

@@@@notes

Wakka

@@@@

@@@

@@@section

Agenda:

1. Programming Languages
1. Function composition instead of layers
1. Abstracting over higher kinded types
1. Compile time implicits vs runtime reflection

Things I would have liked to covered:

@@@@notes

What to expect
* Increasingly more complex
* Scala being a hybrid language really excels
* I used to say you wrote two kinds of Scala
* A huge use case becoming popular in Java, async programming



@@@@

@@@

@@@section

Disclaimer: I do not have a PhD in type theory. If I misuse terms
I am sorry :(

@@@

@@@section

## Scala is a Hybrid Functional / OO language

@@@@notes

* Akka can be very functional/immutable and drop down to imperative
* Vocal pure FP ppl who wish Scala was Haskell
* Only using it as a "better java". Maybe kotlin is a better?
* Unique features. path dependent types, implicits

@@@@

@@@

@@@section

## 1.Programming Languages 

@@@@notes

Starting off really simple
Semantics vs syntax

@@@@

@@@


@@@section

## Semantics vs Syntax

@@@@notes

* You can learn the semantics of a language in a discussion with someone
* Syntax takes lots of typing
* Unfamiliar syntax != complexity
* Think how long you've been using one language's syntax!

@@@@

@@@

@@@section

## Unfamiliar syntax != complexity

@@@@notes

* Fight our natural reaction

@@@@

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

* Only code that does not compile
* Control flow operators in java are statement based

@@@@

@@@

@@@section

### Expressions

@@snip[x]($root$/src/main/java/djava/expressions/ControlFlow.java){#if-mutable}

@@@@notes

* Variables or values the default

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

@@@@notes

A language's default for this is important
simplicity is subjective

@@@@

@@@

@@@section

## Immutability

* Object orientation. Abstract data, provide behaviour.
* Functional. Immutable data. Abstract functions.

@@@@notes

OO immutable is fine

@@@@

@@@

@@@section

## Referential transparency

@@snip[x]($root$/src/main/scala/expressions/ReferentialTransparency.scala){#x}
@@snip[x]($root$/src/main/scala/expressions/ReferentialTransparency.scala){#y .fragment}
@@snip[x]($root$/src/main/scala/expressions/ReferentialTransparency.scala){#x-good .fragment}
@@snip[x]($root$/src/main/scala/expressions/ReferentialTransparency.scala){#x-bad .fragment}

@@@@notes

* Recent blog post
* E.g hitting a database
* Latency
* Do i compose my software with functions or objects?

@@@@

@@@

@@@section

# Function composition

http://batey.info/fs-function-composition

@@@@notes

* FC allows us to compose our systems via separately functions
* Functions that are tested independently
* Functions that may be pure
* They might be methods on objects mu haw ha

@@@@

@@@


@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#web-request}
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#data .fragment}
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#serialisation .fragment}
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#controller .fragment}

@@@@notes

Realistic example
Case classes change the way people program
Being flexible with class location in files
See whole domain in a page

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #service }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #repo .fragment }

@@@@notes

* Layered appraoch
* DI
* Mocking between abstraction layers

@@@@

@@@

@@@section

## Do it yourself wiring

@@@@Notes

* You then feel obliged to test it
* Regardless of paradigm use language features rather than writing boiler plate

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
* Enables partial application

@@@@notes

Scala actually allows arbitrary parameter lists

@@@@

@@@

@@@section

## Partial application

* Provide a subset of the parameters to get a new function

@@@@notes

Why is this important?
World is not made up of add and multiply function
Need ways of massaging our functions so they compose

@@@@

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

@@@@notes

Same code as before

@@@@

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

@@@@notes

This is what we want to get to
Assuming implemented how do we structure our app now?

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #full-request-function }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#web-request}

@@@@notes

* Completely independent
* Don't depend on each other
* Only the types have to line up
* Are we done??
* Func comp not always so easy
* realistically we need DI as well e.g. where does out database connection come from?

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

@@@@notes

* Objects as grouping functions. Static.
* This has broken our function composition
* Main can wire these things together

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #full-request-function }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #web-request}

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-class }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-class-use .fragment }

@@@@notes

* Scala is a FP-OO hybrid
* Think of classes as two argument lists: constructor + methods
* If no member variables

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-use .fragment }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-define .fragment }

@@@@notes

Implicits have to be explicitly in scope
This happens when we "partially apply it"

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

@@@@notes

* Abstracting over them is an advanced feature
@@@@

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

Regular types vs types that need some more types to be types

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
* Same code dealing with a List and an Optional

@@@@

@@@

@@@section

Abstract over a type:

```java
List<A>
```

Abstract over a type constructor:

```java
T<Int>
```
Why not both?

```java
T<A>
```


@@@@notes

Write a method that takes a List<Int>
Or a Set<Int>
With interfaces ^
Or a Optional<Int>
Hrmm? ^^
What about a Future?

@@@@

@@@

@@@section

![x](images/say-what.jpg)

@@@@notes

Notes

@@@@

@@@

@@@section

## Motivation

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #web-request .fragment }

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #web-request-2 .fragment }

@@@@notes

* My background is not in languages that support this
* Async, not a thread per request
* Not using exceptions. GOto with a different name

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #web-request .fragment }
@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #serialize-func .fragment }
@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #customer-func .fragment }
@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #db-func .fragment }
@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #deserialise-create .fragment }


@@@@notes

* Broken our function composition
* DAMNNATIONS

@@@@

@@@

@@@section

```scala
def flatMap(f: A => Future[B]): Future[B]
```

```scala
def map(f: A => B): Future[B]
```

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #save .fragment }

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #full .fragment }

@@@@notes

* Async
* Not using exceptions
* Popular libraries in Scala for doing this type of function composition
* Very common pattern
* Java's optional and completable future have these methods

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/higherkinds/ErrorHandling.scala){ #full2 }

@@@@notes

Notes

@@@@

@@@

@@@section

```scala
f: A => Future[B]
g: B => Future[C]

g <=< f : A => Future[C]

lift(fa: A => B): Future[A] => Future[B]

```

http://eed3si9n.com/herding-cats/composing-monadic-functions.html

https://underscore.io/books/scala-with-cats/

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

@@@@notes

* Each of these sections could be an hour
* If any of that previous section didn't make sense
    * Takeaway: You can write code will work with different types of wrappers
    * We'll see how we can implicitly convert between the "wrapper" types

@@@@

@@@

@@@section

## Which implicit feature don't you like?

@@@

@@@section

## Implicits

@span[Many sensible uses of implicits replace reflection]
@span[Reflection is more implicit than implicits!!] { .fragment }

@@@@notes

You learn how each implicit feature works once
Rather than proprietary framework features

@@@@

@@@

@@@section

## Implicit parameters

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-use .fragment }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-define .fragment }

@@@@notes

* Spark uses this for the SparkContext
* ActorMaterializer

@@@@

@@@

@@@section

## Implicit type conversions

@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #classes }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #clean .fragment }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #ruby .fragment }

@@@@notes

In a static language, no no no
If this is too abstract, let's take a look at Ruby

@@@@

@@@

@@@section

<img src="images/ruby1.jpeg" style="width: 500px;"/>

@@@@notes

Right mind
Awww
Consulted

@@@@

@@@

@@@section

<img src="images/bella1.jpeg" style="width: 700px;"/>

@@@@notes

Not amused

@@@@

@@@

@@@section

## Implicit type conversions

@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #classes }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #clean }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #ruby }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #cleaning .fragment }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #implicit .fragment }


@@@@notes

* Could have a place inside embedded DSLs

@@@@

@@@

@@@section

## Principled type conversions

@@@

@@@section


@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #mappable }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #future-mappable .fragment }
@@snip[x]($root$/src/main/scala/implicits/ImplicitTypeConversions.scala){ #cf-mappable .fragment }

@@@@notes

* Trait that is only converted to implicitly.
* Find all instances

@@@@

@@@

@@@section

### Implicit evidence

@@snip[x]($root$/src/main/scala/implicits/Constraints.scala){ #request-before .fragment }

@@snip[x]($root$/src/main/scala/implicits/Constraints.scala){ #api-example .fragment }

@@snip[x]($root$/src/main/scala/implicits/Constraints.scala){ #execute .fragment }


@@@@notes

* Grpc, single input type "message"

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/implicits/Constraints.scala){ #request-before-unit  }
@@snip[x]($root$/src/main/scala/implicits/Constraints.scala){ #request-before-no-param .fragment }

@@@@notes

Notes

@@@@

@@@

@@@section

@@snip[x]($root$/src/main/scala/implicits/Constraints.scala){ #request-type  }

@@snip[x]($root$/src/main/scala/implicits/Constraints.scala){ #no-compile .fragment  }

@span[`Cannot prove that String =:= Unit. liftedCall.invoke()`] { .fragment }

@@@@notes

Notes

@@@@

@@@


@@@section

### Summary

1. Programming Language
1. Function composition instead of layers
1. Abstracting over higher kinded types
1. Compile time implicits vs runtime reflection

@@@

@@@section

Guidelines for a successful Scala project

* Be prepared to be a beginner
* Pick a programming paradigm
* Pick a style guideline
* Pick a DI style
* Include static analysis

@@@@notes

* Guide should dictate use of implicits
* Wart remover

@@@@

@@@


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