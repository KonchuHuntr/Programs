# Makefile for the RSA_Encryption project

CC = gcc
CFLAGS = -Wall -Wextra -std=c99 -pedantic
INCLUDES = -I. # Includes the current directory for header files

# The final executable name
TARGET = crypto_utility

# List of all source files
SOURCES = EulerTotient.c KeyGen.c ModMultInverse.c

# Automatically generate object file names (.o)
OBJECTS = $(SOURCES:.c=.o)

# Default target: builds the executable
all: $(TARGET)

# Link rule: links all object files into the final executable
$(TARGET): $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(TARGET)

# Compile rule: compiles each .c file into a .o file
# This is where KeyGen.c, EulerTotient.c, and ModMultInverse.c are compiled.
# Since we use the -c flag, they don't need a main() to compile to a .o file.
%.o: %.c
	$(CC) $(CFLAGS) $(INCLUDES) -c $< -o $@

# Clean target: removes compiled files
.PHONY: clean
clean:
	rm -f $(TARGET)
	rm -f $(OBJECTS)
