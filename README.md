# 🥗 Salad's By Prince

A dual-interface meal ordering system.
Users can build custom salads, choose signature bowls, add sides, and drinks.
Includes:

* **Console-based ordering app**
* **Spring Boot Web App** (Prince's Food Court) with HTML UI.

---

## 📚 Table of Contents

* [⚙️ Features](#features)
* [🧩 How-It-Works-Console-App](#how-it-works-console-app)
* [🧠 How-It-Works-Web-App](#how-it-works-princes-food-court-web-app)
* [📊 Class Diagram Overview](#class-diagram-overview)
* [📦 Installation/Run Instructions](#installationrun-instructions)
* [💻 Screen Gifs](#screen-gifs-)
* [💡 Interesting Code Snippet](#interesting-code-snippet)
* [🧪 Test Reports](#test-reports-junit)
* [📜 License](#license)
* [👤 Author](#author)
* [📚 References](#references)

---

## Features

* ✅ Build-your-own salad workflow
* 🥗 Pre-made signature salad menu
* 🍟 Add sides (ex: Sweet Potato, Kimchi, Roasted Tomato)
* 🥤 Drink selection
* 💾 Order summary and checkout
* 🌐 Optional Web UI via Spring Boot + Thymeleaf

---

## How It Works (Console App)

1. User selects Signature Salad **or** Custom Salad
2. If Custom:
    * User chooses greens
    * User adds toppings
    * User selects dressing
3. User optionally adds sides and drinks
4. Final order summary prints
5. An individual receipt is saved to a receipts file

---

## How It Works (Prince's Food Court Web App)

1. Spring Boot serves a Thymeleaf-driven HTML menu
2. Menu items displayed visually
3. Orders stored in session
4. User checks out from browser
5. Prints PDF receipt

---

## Class Diagram Overview

![diagram](images/SaladsByPrinceDiagram.png)


---

## Installation/Run Instructions

### Installation

Clone the repository:
```
git clone https://github.com/praytoo/Capstone2-SaladsByPrince
cd Capstone2-SaladsByPrince
```

### Run

### Console App

```
cd SaladByPrinceConsole
mvn compile
mvn exec:java
```

### Spring Boot Web App

```
cd PrincesFoodCourtWebApp
mvn spring-boot:run
```

Visit:

```
http://localhost:8080
```

---

## Screen Gifs 

Console:
![CLI](images/CLI.gif)

Web App:
![WebApp](images/WebApp.gif)

---

## Interesting Code Snippet

```
class OrderSystemTest {

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        OrderSystem.currentOrder.clear();
    }

    private void provideInput(String input) {
        testIn = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(testIn);
        OrderSystem.scanner = new java.util.Scanner(System.in);
    }
    @Test
    @DisplayName("Check For Salad Greens Accuracy")
    void addSalad() {
        //arrange
        String input = """
                1
                1
                1
                yes
                1
                yes
                1
                yes
                1
                yes
                yes
                cancel
                """;
        provideInput(input);

        OrderSystem.scanner = new Scanner(System.in);

        //act
        OrderSalad.addSalad();

        //assert
        assertEquals(1, OrderSystem.currentOrder.size());
        Salad salad = (Salad) OrderSystem.currentOrder.get(0);

        assertEquals(Size.SMALL, salad.getSize());
        assertEquals("ARUGULA", salad.getGreen().toString());
    }

    @Test
    @DisplayName("Make sure receipt prints accurately")
    void checkout_printsCorrectReceipt() {
        //arrange
        String expectedResult = "\n--- Your Order ---\n" + "Total Price: $0.0\n";
        //act
        String result = ReceiptWriter.generateReceiptText();
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("User Doesn't Confirm Order")
    void readsValidOnFirstTry() {
        //arrange
        var input = new ByteArrayInputStream("no\n".getBytes(StandardCharsets.UTF_8));
        var scanner = new Scanner(input);
        var output = new ByteArrayOutputStream();
        var out = new PrintStream(output);
        boolean input2 = input.equals("no");
        //act
        boolean result = ReceiptWriter.checkout(scanner, out, "no");
        //assert
        assertEquals(input2, result);
        //String text = output.toString(StandardCharsets.UTF_8);
        //assertTrue(text.contains("Order canceled. Returning to home screen."));
    }
}
```
Why it's interesting: Having to test input methods was new to me. I find this piece of code interesting because we test a variety of different outcomes, including input methods. Using ByteArray, PrintStream, and Scanner in my JUnit tests is how I solved the puzzle of unit testing input methods. This code is special to me for that reason.

```
//signature salad list
    public static List<Salad> getSignatureSalads() {
        List<Salad> list = new ArrayList<>();

        Salad balsamicHoney = new Salad(
                Size.SMALL,
                GreenType.ARUGULA,
                new ArrayList<>(List.of(new PremiumTopping("Beef"), new RegularTopping("Raisins"))),
                new Dressing("Balsamic Vinaigrette and Honey"));
        list.add(balsamicHoney);

        Salad chickenCaesar = new Salad(
                Size.SMALL,
                GreenType.LETTUCE,
                new ArrayList<>(List.of(new PremiumTopping("Chicken"), new RegularTopping("Croutons"))),
                new Dressing("Caesar"));
        list.add(chickenCaesar);

        Salad oliveOil = new Salad(
                Size.SMALL,
                GreenType.SPINACH,
                new ArrayList<>(List.of(new PremiumTopping("Tuna"), new RegularTopping("Avocado"))),
                new Dressing("Olive oil and Lemon"));
        list.add(oliveOil);

        return list;
    }
```
Why it's interesting: This snippet shows how the backend of the app dynamically allows you to add a signature salad to your order. I didn't have to make a new class for Signature Salad that extended Salad by making Signature Salad static, so the backend of my app adds a signature salad with compiling ease.


---

## Test Reports (JUnit)

![testreports](images/JUnitTestReport1.png)
![testreports](images/JUnitTestReport2.png)

---
## License

**MIT License**  
This project is open-source and free to use under the terms of the MIT License.  
See the [LICENSE](#LICENSE) file for details.

---

## Author

**Prince Haywood**

📍 Playa Vista, California

👨‍💻 LinkedIn https://www.linkedin.com/in/princehaywood/

## References

Van Putten, M. (2025, November 8th). CLI input and JUnit testing guidance. [Example JUnit Test]. https://github.com/BrightBoost/learningjava/commit/8295b216d59c4bc62dcedf8eb8d6a0ccf76b152b Year Up United.

OpenAI. (2025). ChatGPT (GPT-5) [Large language model]. https://chat.openai.com/ Example code and Spring Boot + Thymeleaf implementation assistance.

Mailjard, R. (2025). Capstone project structure checklist. [Advanced Java OOP – Capstone 2: Custom Food Shop Checklist] https://gist.github.com/RemseyMailjard/e9ab857ca4f92459f40de6d0b75b57fb Year Up United.
