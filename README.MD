# internet-market


## Requirements

- Java 21
- Spring Boot 3.3.4

## How to run

<details close>
  <summary>
    
  </summary>

1. **Clone the repository:**

    ```sh
    git clone https://github.com/Maksud9909/internet-market
    ```
2. **Paste the .env file into assignment-service folder**

   .env file content

    ```.env
    DB_URL=<your-databse-url>
    DB_USERNAME=<your-databse-username>
    DB_PASSWORD=<your-databse-password>
    SECURITY_TOKEN_ACCESS_SECRET_KEY=<repalce-with-generated-secret-koy-for-access-token>
    SECURITY_TOKEN_ACCESS_TIME=<access-token-valid-time-in-millieseconds>
    SECURITY_TOKEN_REFRESH_SECRET_KEY=<repalce-with-generated-secret-koy-for-refresh-token>
    SECURITY_TOKEN_REFRESH_TIME=<refresht-token-valid-time-in-millieseconds>
    ```
3. **Build the project:**

   Use Maven to build the project.

    ```sh
    mvn clean install
    ```

4. **Run the application:**

   To run the application, make sure you have Java 21 installed

5. **Link for the application**

   http://localhost:80/index.html

</details>
