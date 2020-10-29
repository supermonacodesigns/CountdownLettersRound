# Countdown Letters Round Solver 
A small Java application that searches for the longest valid English word that can be created from 9 assigned letters, as per the Letters Round of the "Countdown" television programme shown on Channel 4 UK.

This project utilises a modified version of a dictionary text file from the following project:
https://github.com/dwyl/english-words

Words longer than 9 letters have been filtered out using a short serialization program.  

The 9 letters are checked sequentially against the remaining words in the dictionary text file, and any words that can be created from the 9 letters are added to a TreeSet custom sorted by String length. The words are sent in order to the Oxford Dictionaries API to verify if the word is valid.  If a valid definition is returned the word is added to a list and the API queries terminate if the list size reaches its assigned value (1, by default).

https://developer.oxforddictionaries.com/

Currently the project uses the OkHttp library for API queries.   