# JFramework

JFramework is a lightweight Java-based framework for dependency injection and repository pattern implementation. It leverages Java reflection and dynamic proxies to provide a flexible and extensible architecture for managing dependencies and performing CRUD operations.

## Features

- **Dependency Injection**: Automatically inject dependencies into your classes using custom annotations.
- **Repository Pattern**: Simplifies data access logic with a CRUD repository interface and dynamic proxy implementations.
- **Reflection-Based Initialization**: Uses reflection to discover and initialize components, reducing boilerplate code.
- **Flexible Configuration**: Easily extend and customize the framework to fit your needs.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven or Gradle for dependency management (optional but recommended)

### Installation

1. **Clone the repository**:

   ```sh
   git clone https://github.com/yourusername/jframework.git
   cd jframework
   ```

2. **Compile the project**:

   Using Maven:
   ```sh
   mvn clean install
   ```

   Using Gradle:
   ```sh
   gradle build
   ```

### Usage

1. **Define Your Entities**:

   ```java
   package org.example.TEst;

   public class USER {
       private int id;
       private String name;

       // Getters and setters

       @Override
       public String toString() {
           return "USER{" +
                   "id=" + id +
                   ", name='" + name + '\'' +
                   '}';
       }

       public USER(int id, String name) {
           this.id = id;
           this.name = name;
       }
   }
   ```

2. **Create Repository Interfaces**:

   ```java
   package org.example.TEst;

   import org.example.CrudRepository;

   public interface DataInterface extends CrudRepository<USER, Integer> {
       USER findByName(String name);
   }
   ```

3. **Annotate Fields for Injection**:

   ```java
   package org.example;

   import org.example.Annotations.AutoConfig;
   import org.example.TEst.DataInterface;

   public class Main {

       @AutoConfig
       public static DataInterface inf;

       public static void main(String[] args) {
           new JDBCInitializer().initialize(Main.class);
           inf.findByName("Ayan");
       }
   }
   ```

### How to Use

To use JFramework in your project:

1. **Define your entities and repository interfaces**: Create classes representing your data entities and define repository interfaces extending `CrudRepository`.

2. **Annotate fields for injection**: Use the `@AutoConfig` annotation to mark fields that should be automatically injected by the framework.

3. **Initialize the framework**: In your main application class, call the initialization method to set up the dependencies and repositories.

### Limitations

- **Currently under development**: The framework is in active development and does not yet support database operations. Database support will be added in future releases.

### Contributing

Contributions are welcome! Please fork the repository and submit pull requests for any features, bug fixes, or improvements.

### License

This project is licensed under the MIT License. See the LICENSE file for more details.
