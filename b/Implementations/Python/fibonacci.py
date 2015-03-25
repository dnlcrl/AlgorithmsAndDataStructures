# Fibonacci succession trough python generator
def fibonacci():
    a, b = 0, 1
    yield a
    yield b
    while True:
        a, b = b, a + b
        yield b


# Fibonacci succession number calculation: iterative
def fibonacci_iterative(n):
    fib = [0, 1]
    for i in range(n):
        fib.append(fib[-1] + fib[-2])
    return fib


# Fibonacci succession number calculation: recursive
def fibonacci_recursive(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibonacci_recursive(n - 1) + fibonacci_recursive(n - 2)
