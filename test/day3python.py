letters = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A","B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"]
total = 0
with open("fileinput") as file:
    for line in file:
        half = int(len(line)/2)
        pocket_1 = line[:half]
        pocket_2 = line[half:]


        for letter in pocket_1:
            if letter in pocket_2:
                total += letters.index(letter) + 1
                break

print(total)

print(total)
