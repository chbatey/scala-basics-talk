# Scala: Beyond the basics 

This repo contains all the resources associated with my talk "Scala: Beyond the basics"

## Generating

`sbt paradox` will generate the presentation and place it in
`target/paradox/main/site`

The presentation itself is a [paradox](https://github.com/lightbend/paradox)
markdown file in `src/main/paradox/index.md`. Code snippets are taken from
`src/main/java` and `src/main/scala`.

The paradox theme in `src/main/paradox/_theme` formats the output as a
[reveal.js](https://revealjs.com) HTML presentation. This could be split out
as a separate deliverable if it proves useful. The reveal.js theme in
`src/main/paradox/_template/css/theme/akka.css adds some Akka fonts and colors.

