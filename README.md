# matrix-java

[![Java Version](https://img.shields.io/badge/java-21+-blue.svg)](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

`matrix-java` is a lightweight, fast, and easy-to-use Java library for linear algebra.

It provides immutable and mutable matrix implementations, common matrix operations, and utility methods for creating and manipulating matrices.

## Features

* Immutable and mutable matrix classes
* Support for basic arithmetic operations
* Transpose, trace, norms (L1, infinity)
* Structural checks: square, zero, identity, diagonal, etc
* Simple, consistent API with defensive copying for safety
* Lightweight, no external dependencies

## Installation

### Maven

```xml
<dependency>
    <groupId>com.gmeunier</groupId>
    <artifactId>matrix-java</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Manual

Download the JAR from the [releases](https://github.com/guiguitator/matrix-java/releases) page and add it to your project's classpath.

## Usage

```java
import com.gmeunier.matrix.*;

ImmutableMatrix a = Matrix.of(new double[][] {
    {1, 2},
    {3, 4}
});

ImmutableMatrix b = Matrix.of(new double[][] {
    {5, 6},
    {0, 8}
});

Matrix c = b.subtract(a);

if (c.isUpperTriangular()) {
    MutableMatrix m = c.toMutable();
    
    double value = m.get(1, 0);
    m.set(value);
}
```

## Documentation

- **Javadoc**: file `matrix-java-*-javadoc.jar` downloadable from the [releases](https://github.com/guiguitator/matrix-java/releases) page
- **Releases**: all versions are available on the [releases](https://github.com/guiguitator/matrix-java/releases) page
- **Changelog**: changes between versions listed on [GitHub releases](https://github.com/guiguitator/matrix-java/releases) and in [CHANGELOG.md](CHANGELOG.md)

## Building

Git, Maven and JDK 21 (or newer) are required for building.

```bash
git clone https://github.com/guiguitator/matrix-java.git
cd matrix-java
mvn clean install
```

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE.txt) file for details.