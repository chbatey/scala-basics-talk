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

## Currying

* Each function takes one argument
* Allows partial application

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

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-pa }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-pa-use .fragment }

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){ #full-request-function }
@@snip[x]($root$/src/main/scala/layers/LayersVsComposition.scala){#web-request}

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-class }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-class-use .fragment }

@@@

@@@section

@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-use .fragment }
@@snip[x]($root$/src/main/scala/layers/WebRequestRealistic.scala){ #data-access-implicit-define .fragment }

@@@


@@@section

##TODO

* Exaplain functiono as val vs def
* Representing constraints with implicits

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