# `com.tomuvak.weak-reference` – a multi-platform Kotlin library with a unified interface for weak references
[![Licence][1]][2]
[![Latest release version][3]][4]
[![Build and tests status][5]][6]

This library is licensed under the [MIT License](https://en.wikipedia.org/wiki/MIT_License);
see [LICENSE.txt](LICENSE.txt).

## Rationale
The functionality of [weak references](https://en.wikipedia.org/wiki/Weak_reference) is available for the JVM through
the [Java standard library](https://docs.oracle.com/javase/8/docs/api/java/lang/ref/WeakReference.html), it exists also
in [JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/WeakRef), and it is
offered by the standard library for
[Native](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.native.ref/-weak-reference/).
Yet, the standard library offers no unified interface for it which would allow weak references to be used seamlessly in
multi-platform code.
This library, which split from [`com.tomuvak.util`](https://github.com/tomuvak/util) for a technical reason, aims to
provide a remedy for that.

[1]: https://img.shields.io/github/license/tomuvak/weak-reference?label=Licence
[2]: LICENSE.txt
[3]: https://img.shields.io/github/v/tag/tomuvak/weak-reference?label=Latest%20release
[4]: https://github.com/tomuvak/weak-reference/tags
[5]: https://github.com/tomuvak/weak-reference/actions/workflows/check-on-push.yaml/badge.svg
[6]: https://github.com/tomuvak/weak-reference/actions/workflows/check-on-push.yaml
