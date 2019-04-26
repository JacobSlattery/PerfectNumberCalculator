# PerfectNumberCalculator
This application was designed to test the efficiency of multithreading by making a function which calculates all of the perfect
numbers up to 3 billion while maintaining the ability to go to higher numbers. In order to show the true power of multithreaded 
applications, the tasks should have been made less efficient, but I got a little carried away with it and wanted to see how fast 
I could get it to run. The threading was made less necessary, but the program was optimized to run over 1000% more efficiently by 
relying on mathematic proofs which indicate all perfect numbers must also be prime numbers. Since prime numbers can be generated 
with a simple equation, the program generates a series of primes as guesses and assigns them to threads which check to see if 
they are perfect numbers.

Since the ultimate goal was to show the usefulness of running multiple threads, the Driver class runs a series of tests which
find the length of time spent on the calculations per thread count. Each test is repeated numerous times in order to stabilize 
the data and reduce the impact of anomalies. I wrote a short summary paper containing my findings and included some graphs, because
I love graphs, which show the trends of threading efficiency and their relation to the CPU core count.
