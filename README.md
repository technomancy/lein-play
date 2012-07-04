# lein-play

Play sounds when your tests pass or fail.

## Installation

Add this to `:plugins` in your `:user` profile in `~/.lein/profiles.clj`:

    [lein-play "2.0.0"]

And this to the `:hooks` entry:

    `leiningen.hooks.play`

Place a pass.mp3 or fail.mp3 in `~/.lein` to override the built-in sounds.

## License

Code Copyright © 2010, 2012 Phil Hagelberg

The code is licensed under the Eclipse Public License.

The sound effects are licensed under Creative Commons Attribution
Sharealike license.
