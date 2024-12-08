-- Create Authors table
CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         bio TEXT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Create Books table
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    author_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);



-- Insert authors
INSERT INTO authors (name, bio) VALUES
('J.K. Rowling', 'Author of the Harry Potter series'),
('George R.R. Martin', 'Author of A Song of Ice and Fire'),
('J.R.R. Tolkien', 'Author of The Lord of the Rings');

-- Insert books
INSERT INTO books (title, isbn, price, author_id) VALUES
('Harry Potter', '9780747532699', 19.99, 1),
('A Game of Thrones', '9780553103540', 29.99, 2),
('The Hobbit', '9780345339683', 15.99, 3);