# JSP exercises

## Setup

This project uses Docker and Docker compose.

### Mac

First download and install Docker for Mac from 
[here](https://docs.docker.com/docker-for-mac/), use the stable
release.

You'll also need `envsubst`. Verify that's installed by running

```bash
$ envsubst -h
```

If you don't have it installed, use Homebrew to install it.

```bash
$ brew install gettext
$ brew link --force gettext
```

## Installation

Build the containers with the following command

```bash
$ make install
```

This will start the Tomcat container. Now you can now browse to
[http://localhost/web/](http://localhost/web).

## Usage

To start the Tomcat container run:

```bash
docker-compose up -d tomcat
```
