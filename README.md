# Instalación de Selenium y xUnit para Java [ESP]

1. [Inicialización de Proyecto en Java con Maven](#inicialización-de-proyecto-en-java-con-maven)
2. [Instalar y Agregar Selenium, JUnit y WebDriver](#instalar-y-agregar-selenium-junit-y-webdriver)
3. [Ejemplo de Uso de Selenium, JUnit y WebDriver](#ejemplo-de-uso-de-selenium-junit-y-webdriver)
4. [Definir los Casos de Prueba usando BlackBox](#definir-los-casos-de-prueba-usando-blackbox)
5. [Ejecución de `AppTest.java`](#ejecución-de-apptestjava)

## Inicialización de Proyecto en Java con Maven

1. **Abre la terminal:**

   - **Windows:** Abre el "Símbolo del sistema" o "Command Prompt" escribiendo `cmd` en la barra de búsqueda.
   - **macOS:** Ve a Applications > Utilities > Terminal.
   - **Linux:** Usa Ctrl + Alt + T para abrir la terminal.

2. **Navega hasta la carpeta deseada:**

   - Utiliza `cd` seguido de la ruta de la carpeta para cambiar al directorio deseado: `cd /ruta/de/la/carpeta`.

3. **Crea un nuevo proyecto Maven:**

   - Ejecuta el siguiente comando Maven para crear un proyecto básico:
     ```bash
     mvn archetype:generate -DgroupId=com.ejemplo -DartifactId=mi-proyecto -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
     ```
     - `groupId`: Identificador del grupo para tu proyecto.
     - `artifactId`: Nombre del proyecto.
     - `archetypeArtifactId`: Tipo de arquetipo a utilizar (`maven-archetype-quickstart`).

4. **Ingresa al directorio del proyecto:**

   - Muévete al directorio del proyecto recién creado usando `cd`:
     ```bash
     cd mi-proyecto
     ```

5. **Verifica la estructura del proyecto:**
   - Usa `ls` (Linux/macOS) o `dir` (Windows) para verificar la estructura del proyecto.

## Instalar y Agregar Selenium, JUnit y WebDriver

- **Selenium** es una herramienta de automatización de pruebas de software para navegadores web. Permite interactuar con un navegador web de manera programática y simular acciones del usuario.

- **JUnit** es un marco de trabajo (framework) de pruebas unitarias para Java que permite escribir y ejecutar pruebas de manera sencilla.

### Configuración de Dependencias

Para agregar Selenium, JUnit y WebDriver a un proyecto Maven, debes editar el archivo `pom.xml` y agregar las dependencias necesarias:

```xml
<dependencies>
    <!-- Dependencia Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.15.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Dependencia Selenium Chrome WebDriver -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-chrome-driver</artifactId>
        <version>4.15.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Dependencia JUnit -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Esto asegurará que Maven descargue las bibliotecas necesarias para usar Selenium y JUnit en tu proyecto.

### Instalación del WebDriver de Chrome

Para utilizar Selenium WebDriver con Chrome, necesitas descargar el WebDriver correspondiente y configurar su ubicación.

1.  **Descarga del ChromeDriver:**

    - Visita el sitio oficial de ChromeDriver (https://googlechromelabs.github.io/chrome-for-testing/).
    - Descarga la versión compatible con tu versión de Chrome.
      ![Chrome Version](/img/chrome_version.png)

2.  **Colocación del ChromeDriver:**

    - Descomprime el archivo descargado y coloca el ejecutable `chromedriver` en una ubicación accesible.
    - Por ejemplo, puedes colocarlo en una carpeta llamada `drivers` dentro de tu proyecto.

3.  **Configuración en el código:**

    En tu código Java, establece la propiedad `webdriver.chrome.driver` apuntando a la ubicación del archivo `chromedriver`:

    ```java
    System.setProperty("webdriver.chrome.driver", "ruta/del/chromedriver");
    ```

Con estos pasos, habrás configurado las dependencias de Selenium y JUnit en tu proyecto Maven y preparado el WebDriver de Chrome para su uso con Selenium WebDriver.

## Ejemplo de Uso de Selenium, JUnit y WebDriver

En este ejemplo, se utilizan las bibliotecas Selenium, JUnit y WebDriver para realizar pruebas automatizadas en una calculadora de porcentaje en línea. El objetivo es verificar el cálculo del porcentaje con diferentes conjuntos de datos de entrada.

### Archivo AppTest.java

Este archivo contiene las pruebas unitarias escritas con JUnit.

- La clase `AppTest` se extiende a `TestCase` para aprovechar la funcionalidad de JUnit 3. Esta clase base de prueba proporciona métodos y utilidades para configurar y ejecutar pruebas unitarias. En este contexto, `setUp()` se utiliza para inicializar el entorno de prueba antes de cada método de prueba, mientras que `tearDown()` se encarga de liberar recursos después de la ejecución de cada prueba.

```java
    public class AppTest extends TestCase
```

- En el método `tests()`, se definen conjuntos de datos de entrada para probar la función `calculatePercentage()` definida en `AppWebdriver.java`, y luego comprobando el dato obtenido con el esperado usando `assetEquals()`.

```java
    String result = AppWebdriver.calculatePercentage(driver, data[0], data[1]);
    System.out.println("Para " + data[0] + " y " + data[1] + " el resultado es: " + result);
    assertEquals(data[2], result.trim());
```

- Se inicializa el WebDriver en el método `setUp()` y se liberan los recursos en `tearDown()`.

```java
    protected void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/testweb/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
    }

    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
```

### Archivo AppWebdriver.java

En esta se proporciona una función llamada `calculatePercentage()` que recibe el WebDriver, los valores de entrada y realiza la interacción con la calculadora en línea. Utiliza Selenium WebDriver para encontrar los elementos de la página web, ingresar valores y obtener el resultado del cálculo de porcentajes.

## Definir los Casos de Prueba usando BlackBox

La estrategia de prueba basada en caja negra implica diseñar casos de prueba utilizando la especificación de los requisitos funcionales y sin conocer el código interno. Para el problema de la calculadora de porcentaje, podríamos emplear una combinación de clases de equivalencia y valores límite para diseñar casos de prueba efectivos.

#### Estrategia basada en Clases de Equivalencia:

- **Clase de Equivalencia 1:** Porcentajes positivos (mayores que cero).
- **Clase de Equivalencia 2:** Porcentajes negativos (menores que cero).
- **Clase de Equivalencia 3:** Porcentaje igual a cero.

#### Estrategia basada en Valores Límite:

- **Valor límite inferior:** Probar el valor más pequeño posible (cero).
- **Valor límite superior:** Probar el valor más grande permitido.

#### Generación de Tablas de Decisión:

| Escenario de Prueba   | Valores de Prueba              | Resultado Esperado |
| --------------------- | ------------------------------ | ------------------ |
| Porcentaje positivo   | Porcentaje: 15.5, Número: 7.2  | Resultado: 1.116   |
| Porcentaje negativo   | Porcentaje: -15.5, Número: 7.2 | Resultado: -1.116  |
| Porcentaje cero       | Porcentaje: 0, Número: 7.2     | Resultado: 0       |
| Valor límite inferior | Porcentaje: 0.01, Número: 7.2  | Resultado: 0.00072 |
| Valor límite superior | Porcentaje: 100, Número: 7.2   | Resultado: 7.2     |

Estos enfoques permiten diseñar una variedad de casos de prueba que cubren distintas condiciones y límites, asegurando la robustez y precisión en el cálculo del porcentaje en la calculadora.

## Ejecución de `AppTest.java`

Para ejecutar este ejemplo desde la terminal, sigue estos pasos:

1. Ubícate en el directorio donde se encuentran los archivos del proyecto.

2. Ejecuta el siguiente comando para ejecutar las pruebas definidas en `AppTest.java`:

```bash
mvn test
```

![Compile Test](/img/compile_test.png)

La ejecución de las pruebas fue exitosa y los resultados coinciden con lo esperado. Al utilizar el comando `mvn test`, todas las pruebas definidas en el archivo `AppTest.java` se ejecutaron correctamente.

La salida muestra que se ejecutaron cinco conjuntos de datos de prueba, cada uno con diferentes valores de entrada para el cálculo de porcentajes. Para cada conjunto de datos, se imprimió en la terminal el resultado obtenido del cálculo y coincidió con los valores esperados.

Los resultados finales indican que las pruebas no presentaron fallos (`Failures: 0`) ni errores (`Errors: 0`). Además, ninguna prueba fue omitida (`Skipped: 0`). Por lo tanto, la ejecución concluye con un mensaje de `BUILD SUCCESS`, confirmando que todas las pruebas se realizaron correctamente dentro de un tiempo de ejecución razonable.

Esto demuestra que la automatización de pruebas con Selenium, JUnit y WebDriver se llevó a cabo de manera efectiva y que la aplicación bajo prueba responde correctamente a los diferentes conjuntos de datos de entrada.

---

# Installation of Selenium and xUnit for Java [ENG]

1. [Initializing a Java Project with Maven](#initializing-a-java-project-with-maven)
2. [Installing and Adding Selenium, JUnit, and WebDriver](#installing-and-adding-selenium-junit-and-webdriver)
3. [Example of Using Selenium, JUnit, and WebDriver](#example-of-using-selenium-junit-and-webdriver)
4. [Defining Test Cases using BlackBox](#defining-test-cases-using-blackbox)
5. [Executing `AppTest.java`](#executing-apptestjava)

## Initializing a Java Project with Maven

1. **Open the terminal:**

   - **Windows:** Open "Command Prompt" by typing `cmd` in the search bar.
   - **macOS:** Go to Applications > Utilities > Terminal.
   - **Linux:** Use Ctrl + Alt + T to open the terminal.

2. **Navigate to the desired folder:**

   - Use `cd` followed by the folder path to change to the desired directory: `cd /path/to/folder`.

3. **Create a new Maven project:**

   - Run the following Maven command to create a basic project:
     ```bash
     mvn archetype:generate -DgroupId=com.example -DartifactId=my-project -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
     ```
     - `groupId`: Group identifier for your project.
     - `artifactId`: Project name.
     - `archetypeArtifactId`: Type of archetype to use (`maven-archetype-quickstart`).

4. **Navigate to the project directory:**

   - Move to the newly created project directory using `cd`:
     ```bash
     cd my-project
     ```

5. **Verify the project structure:**
   - Use `ls` (Linux/macOS) or `dir` (Windows) to check the project structure.

## Installing and Adding Selenium, JUnit, and WebDriver

- **Selenium** is a software testing automation tool for web browsers. It allows interacting with a web browser programmatically and simulating user actions.

- **JUnit** is a unit testing framework for Java that enables writing and executing tests easily.

### Dependency Configuration

To add Selenium, JUnit, and WebDriver to a Maven project, edit the `pom.xml` file and add the necessary dependencies:

```xml
<dependencies>
    <!-- Selenium Dependency -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.15.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Selenium Chrome WebDriver Dependency -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-chrome-driver</artifactId>
        <version>4.15.0</version>
        <scope>test</scope>
    </dependency>

    <!-- JUnit Dependency -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

This ensures that Maven downloads the necessary libraries to use Selenium and JUnit in your project.

### Chrome WebDriver Installation

To use Selenium WebDriver with Chrome, you need to download the corresponding WebDriver and configure its location.

1. **Download ChromeDriver:**

   - Visit the official ChromeDriver site (https://googlechromelabs.github.io/chrome-for-testing/).
   - Download the version compatible with your Chrome version.
     ![Chrome Version](/img/chrome_version.png)

2. **Placement of ChromeDriver:**

   - Unzip the downloaded file and place the `chromedriver` executable in an accessible location.
   - For example, you can place it in a folder named `drivers` within your project.

3. **Code Configuration:**

   In your Java code, set the `webdriver.chrome.driver` property pointing to the location of the `chromedriver` file:

   ```java
   System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
   ```

   With these steps, you will have configured the dependencies of Selenium and JUnit in your Maven project and prepared the Chrome WebDriver for use with Selenium WebDriver.

## Example of Using Selenium, JUnit, and WebDriver

In this example, the Selenium, JUnit, and WebDriver libraries are used to perform automated tests on an online percentage calculator. The goal is to verify the percentage calculation with different sets of input data.

### File AppTest.java

This file contains unit tests written with JUnit.

- The `AppTest` class extends `TestCase` to take advantage of JUnit 3 functionality. This base test class provides methods and utilities to set up and execute unit tests. In this context, `setUp()` is used to initialize the test environment before each test method, while `tearDown()` is responsible for releasing resources after each test execution.

```java
    public class AppTest extends TestCase
```

- In the `tests()` method, input data sets are defined to test the `calculatePercentage()` function defined in `AppWebdriver.java`, and then checking the obtained data with the expected using `assetEquals()`.

```java
    String result = AppWebdriver.calculatePercentage(driver, data[0], data[1]);
    System.out.println("For " + data[0] + " and " + data[1] + " the result is: " + result); assertEquals(data[2], result.trim());
```

- The WebDriver is initialized in the `setUp()` method, and resources are released in `tearDown()`.

```java
    protected void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/testweb/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
    }

    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
```

### File AppWebdriver.java

This file provides a function named `calculatePercentage()` that receives the WebDriver, input values, and interacts with the online calculator. It uses Selenium WebDriver to find elements on the web page, enter values, and obtain the percentage calculation result.

## Defining Test Cases using BlackBox

The black-box testing strategy involves designing test cases using the specification of functional requirements without knowing the internal code. For the percentage calculator problem, a combination of equivalence classes and boundary values could be employed to design effective test cases.

#### Equivalence Classes-based Strategy:

- **Equivalence Class 1:** Positive percentages (greater than zero).
- **Equivalence Class 2:** Negative percentages (less than zero).
- **Equivalence Class 3:** Percentage equal to zero.

#### Boundary Values-based Strategy:

- **Lower Boundary Value:** Testing the smallest possible value (zero).
- **Upper Boundary Value:** Testing the largest allowed value.

#### Decision Table Generation:

| Test Scenario        | Test Values                    | Expected Result |
| -------------------- | ------------------------------ | --------------- |
| Positive percentage  | Percentage: 15.5, Number: 7.2  | Result: 1.116   |
| Negative percentage  | Percentage: -15.5, Number: 7.2 | Result: -1.116  |
| Zero percentage      | Percentage: 0, Number: 7.2     | Result: 0       |
| Lower Boundary Value | Percentage: 0.01, Number: 7.2  | Result: 0.00072 |
| Upper Boundary Value | Percentage: 100, Number: 7.2   | Result: 7.2     |

These approaches allow designing a variety of test cases covering different conditions and limits, ensuring robustness and accuracy in the percentage calculation in the calculator.

## Executing `AppTest.java`

To run this example from the terminal, follow these steps:

1. Navigate to the directory where the project files are located.

2. Run the following command to execute the tests defined in `AppTest.java`:

```bash
mvn test
```

![Compile Test](/img/compile_test.png)

The test execution was successful, and the results match the expected outcomes. By using the `mvn test` command, all tests defined in the `AppTest.java` file were executed successfully.

The output shows that five sets of test data were executed, each with different input values for percentage calculations. For each data set, the result obtained from the calculation was printed in the terminal and matched the expected values.

The final results indicate that the tests had no failures (`Failures: 0`) or errors (`Errors: 0`). Additionally, no tests were skipped (`Skipped: 0`). Therefore, the execution concludes with a `BUILD SUCCESS` message, confirming that all tests were performed correctly within a reasonable execution time.

This demonstrates that the test automation with Selenium, JUnit, and WebDriver was carried out effectively, and the tested application responded correctly to different sets of input data.
