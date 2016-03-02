# Connect Four

## Introduction

The primary objective of this homework is to develop the game of
[Connect Four](https://en.wikipedia.org/wiki/Connect_Four). This is a
two-player game in which players take turns putting chips on a
grid. Once a player has four chips in-a-row they win. See the game's
original [television commercial](https://youtu.be/9KsfiqAdSW0) for a
visual summary.

Connect Four is an instance of a larger class of games known as
"[m,n,k-games](https://en.wikipedia.org/wiki/M,n,k-game)". These games
not only happen to be fun, but also lend themselves well to object
oriented practices. Thus, this assignment is also laying the
groundwork for future exercises.

Aside from developing a game, this homework should get you comfortable
with unit testing -- you'll notice a JUnit file buried in the test
package -- and with the [observer design
pattern](https://en.wikipedia.org/wiki/Observer_pattern).

## Classes

There are two abstract classes provided for you: `Game` and `View`,
located in `src/api`. It is your job to implement these classes
`impl`.

Package | Class | Extends | Implements | Summary
     ---|    ---|      ---|         ---|        ---
api  | View | | Observer | Handles visual information: User input from `stdin`, visualisation of the board to `stdout`.
     | Game | Observable | | Manages the internal game state.
impl | ConnectFourView | View | | Your implementation of View.
     | ConnectFourGame | Game | | Your implementation of Game.

## Testing

The `test` package comes with an overall system test, and a series of
unit tests. You can use the system test for sanity checking, but your
focus should be the unit tests. There are several standard tests you
must pass, but you should also create your own tests for areas that
might not be covered.

Within the `test` directory, there is a sub-directory for unit tests
(`tests/unit`). This directory contains another directory containing
the standard set of tests (`jsw7`) -- you should create another
directory alongside this containing your tests. Specifically, if your
NetID is `bold24`, you might do the following:
```
$> cd src/test/unit/
$> ls
jsw7
$> mkdir bold24
```
and put your unit tests in `bold24`.

Note, there are no standard tests for the view (`ConnectFourView`) nor
are you required to create any (although, feel free to if you're so
inclined).

## Build/test/run

You can use whatever you like for development. If you choose to remain
in the command line, the ant build script recognises the following
commands:

Command | Description
     ---|            ---
clean   | Removes extraneous directories; most notably, existing class files. Use this command if Java's giving you strange errors.
compile | Compiles all Java files under `src`.
test    | Engages JUnit to run the unit tests.
run     | Runs the full game.

## Workflow

For this homework, it is expected that you will use GitHub
religiously. You should fork this project and [make your fork
private](https://help.github.com/articles/making-a-public-repository-private/). You
can then `branch`/`commit`/`push` freely. You should submit your final
code to NYU Classes. Once all submissions are in, you can make your
repository public.
