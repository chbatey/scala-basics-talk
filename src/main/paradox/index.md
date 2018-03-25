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

@@snip[x]($root$/src/main/java/ss/ControlFlow.java){#if-mutable}

@@@@notes

Notes

@@@@

@@@

@@@section

### Expressions

@@snip[x]($root$/src/main/java/ss/ControlFlow.java){#if-uninit}

@@@@notes

Notes

@@@@

@@@

@@@section

### Expressions

What does this evaluate to in Scala?

@@snip[x]($root$/src/main/scala/scala/ss/ControlFlow.scala){#if-expression}

@@@@notes

Notes

@@@@

@@@

@@@section

### Expressions

@@snip[x]($root$/src/main/scala/scala/ss/ControlFlow.scala){#if-assign}

@@@@notes

Notes

@@@@

@@@

@@@section

### Expressions

@@snip[x]($root$/src/main/scala/scala/ss/ControlFlow.scala){#if-short}

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

## Happy hAkking!

Slides & Code
:  [github.com/raboof/akka-http-backpressure](https://github.com/raboof/akka-http-backpressure)

Docs & QuickStarts
:  [akka.io](https://akka.io), [developer.lightbend.com/start](https://developer.lightbend.com/start)

Community
: [gitter.im/akka/akka](https://gitter.im/akka/akka)

Tweet
: [@akkateam](https://twitter.com/akkateam), [@raboofje](https://twitter.com/raboofje)

@notes[And talk to me afterwards :). Time: 12:45-12:55]

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