# YamahaPruebaTecnica
Prueba tecnica para Incolmotos Yamaha

# Iniciar proyecto
- Para iniciar el proyecto primero tendremos que tener nuestra base de ya creada.
  - Se pueden crear las entidades con Hibernate o con el siguiende Script SQL
  ```sql
    CREATE TABLE users (
    id_user INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    full_name_user VARCHAR(255),
    user_email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
    );
    
    CREATE TABLE clients (
    client_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    identification_number VARCHAR(20) NOT NULL UNIQUE,
    names VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    date_of_birth DATE NOT NULL,
    gender VARCHAR(20) NOT NULL,
    cell_phone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    id_user BIGINT,
    FOREIGN KEY (id_user) REFERENCES users(id_user)
    );
    
    CREATE TABLE vehicles (
    vehicle_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    engine_number VARCHAR(50) NOT NULL UNIQUE,
    model VARCHAR(50) NOT NULL,
    cylinder_capacity INT NOT NULL,
    color VARCHAR(30) NOT NULL,
    date_of_assembly DATE NOT NULL,
    year_model INT NOT NULL
    );
    
    CREATE TABLE sales (
    sale_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    date DATE NOT NULL,
    invoice_number VARCHAR(20) NOT NULL UNIQUE,
    city VARCHAR(50) NOT NULL,
    store VARCHAR(100) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    vehicle_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    sales_person VARCHAR(100) NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id),
    FOREIGN KEY (client_id) REFERENCES clients(client_id)
    );
    
    CREATE TABLE refresh_tokens (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    version BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES users(id_user)
    );
    ```
  Y para tener unos datos de prueba los siguientes scripts:
    ```sql
    INSERT INTO users (full_name_user, password, role, user_email)
    VALUES ('Yamaha Prueba', '$2a$10$eIl//LlyELB.KUF6vqu4fu6E8s/OfQAN0hMJXjNwtfzIBnj/dmXSG', 'ADMIN', 'yamaha@yamaha.com');
  
    INSERT INTO clients (identification_number, names, last_name, address, date_of_birth, gender, cell_phone, email, id_user)
    VALUES ('123456789', 'Juan Pablo', 'Girald Tamayo', 'Call344#26', '2004-03-24', 'Hombre', '3165328746', 'juanpablo@yamaha.com', 1);
  
    INSERT INTO vehicles (engine_number, model, cylinder_capacity, color, date_of_assembly, year_model)
    VALUES
    ('1M9N654321', 'Yamaha YZF-R3', 321, 'Blue', '2023-05-15', 2023),
    ('1M9N654322', 'Yamaha MT-07', 689, 'Black', '2022-08-10', 2022),
    ('1M9N654323', 'Yamaha FZ-09', 847, 'Red', '2021-07-20', 2021),
    ('1M9N654324', 'Yamaha VMAX', 1679, 'Silver', '2020-04-18', 2020),
    ('1M9N654325', 'Yamaha YZF-R1', 998, 'Yellow', '2023-02-05', 2023);
  ```
La contraseña del usuario yamaha@yamaha.com es: Yamaha2024!
- Luego tendremos que implementar nuestras variables de entorno como en el example.env creando un archivo ".env" en la carpeta resources:
```java
DATABASE_URL={}
DATABASE_USERNAME={}
DATABASE_PASSWORD={}
JWT_SECRET={}
```
Ya puedes utilizar el aplicativo completamente.
En el http://localhost:8080/api/1.0/swagger-ui/index.html#/ se puede encontrar la documentacion de los endpoint
