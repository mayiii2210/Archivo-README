-- Active: 1725573407250@@127.0.0.1@5432@secretosparacontar_db
CREATE TABLE "clave" (
  "id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "user_id" integer,
  "hashClave" varchar(100) NOT NULL,
  "isActive" bit NOT NULL,
  "modification" timestamp NOT NULL
);

CREATE TABLE "user" (
  "id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "username" varchar(30) NOT NULL,
  "lastname" varchar(30),
  "doc_type" varchar(30) NOT NULL,
  "doc" varchar(10) UNIQUE NOT NULL,
  "email" varchar UNIQUE NOT NULL,
  "tel" varchar(10),
  "birth" date,
  "image" varchar(1000),
  "bio" text,
  "alias" varchar(20),
  "isActive" bit NOT NULL
);

CREATE TABLE "user_rol" (
  "id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "user_id" integer NOT NULL,
  "rol_id" integer NOT NULL,
  "isActive" bit,
  "modification" timestamp
);

CREATE TABLE "rol" (
  "id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "name" varchar(15) NOT NULL,
  "isActive" bit
);

CREATE TABLE "books" (
  "id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "bookname" varchar(500) NOT NULL UNIQUE,
  "author" varchar(100),
  "gender" varchar(100) NOT NULL,
  "url_image" varchar(1000) UNIQUE,
  "url_pdf" varchar(1000) NOT NULL UNIQUE,
  "url_audio" varchar(1000) UNIQUE,
  "description" text,
  "isActive" bit 
);

CREATE TABLE "user_book" (
  "id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "user_id" integer,
  "book_id" integer,
  "bookname" varchar(1000),
  "progress" integer,
  "rating" integer NULL,
  "isFav" bit 
);

CREATE TABLE "cat_book" (
  "id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "category_id" integer,
  "book_id" integer
);

CREATE TABLE "category" (
  "id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "nameCategory" varchar(100) NOT NULL,
  "isActive" bit NOT NULL
);

CREATE TABLE "logs" (
  "id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "description" varchar(256) NOT NULL,
  "error_stack" text NOT NULL,
  "timestamp" timestamp NOT NULL
);

ALTER TABLE "clave" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "user_rol" ADD FOREIGN KEY ("rol_id") REFERENCES "rol" ("id");

ALTER TABLE "user_rol" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "user_book" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "user_book" ADD FOREIGN KEY ("book_id") REFERENCES "books" ("id");

ALTER TABLE "books" ADD FOREIGN KEY ("id") REFERENCES "cat_book" ("book_id");

ALTER TABLE "cat_book" ADD FOREIGN KEY ("category_id") REFERENCES "category" ("id");

INSERT INTO "rol" ("name", "isActive") VALUES
  ('Admin',B'1'),
  ('Moderador',B'1'),
  ('Usuario',B'1');

