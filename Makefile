# Define variables
JAVAC = javac
JAVA = java
SRCS = Interactive.java PasswordEncryptor.java PasswordDecryptor.java
CLASS_FILES = Interactive PasswordEncryptor PasswordDecryptor

# Default target to compile the Java program
all: $(CLASS_FILES)

# Compile the Java source files into class files
$(CLASS_FILES): $(SRCS)
	$(JAVAC) $(SRCS)

# Run the Java program
run: $(CLASS_FILES)
	$(JAVA) Interactive

# Clean up compiled class files
clean:
	rm -f *.class
