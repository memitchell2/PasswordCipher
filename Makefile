# Define variables
JAVAC = javac
JAVA = java
SRC = Interactive.java
CLASS = Interactive

# Default target to compile the Java program
all: $(CLASS)

# Compile the Java source file into a class file
$(CLASS): $(SRC)
	$(JAVAC) $(SRC)

# Run the Java program
run: $(CLASS)
	$(JAVA) $(CLASS)

# Clean up compiled class files
clean:
	rm -f *.class
