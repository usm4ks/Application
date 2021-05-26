CONNECT 'jdbc:postgresql://localhost:5432/application;create=true;user=maksusatenko;password=';
DROP TABLE IF EXISTS Books;

CREATE TABLE Books(
                      id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      title VARCHAR(30) NOT NULL,
                      author VARCHAR(20) NOT NULL,
                      amount INT NOT NULL
);
