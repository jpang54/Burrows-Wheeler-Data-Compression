# Burrows-Wheeler

Burrows-Wheeler is an easy-to-implement data compression algorithm that outcompresses *[gzip](https://github.com/jpang54/Burrows-Wheeler-Data-Compression/blob/main/runtime-testing.txt)* (see compression ratio table) and *PKZIP*, and is also the basis of *bzip2*.

There are 3 components to this algorithm, applied in order:
1. *Burrows-Wheeler transform*: Given a typical English text file, transform the file such that sequences of the same character appear near each other many times.
2. *Move-to-front encoding*: Given a text file where sequences of the same character appear near each other many times, transform the file such that certain characters appear more frequently than others.
3. *Huffman compression*: Given a text file where certain characters appear more frequently than others, compress the file such that characters that appear more often are encoded with short codewords while infrequent characters are encoded with long codewords.
