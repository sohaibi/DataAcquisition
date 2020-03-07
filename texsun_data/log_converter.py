import csv
import os

hit_space = False

# loop through files in log folder
for file in os.listdir("."):
    if file.endswith(".log"):
        base = os.path.splitext(file)[0]
        formatted = base + "-F.log"
        # preprocess file to remove spaces
        with open(file, 'r') as infile, open(formatted, 'w') as outfile:
            for line in file:
                for char in line:
                    temp = infile.read(1)
                    if char == " " and not hit_space:
                        hit_space = True
                    elif hit_space and char == " ":
                        temp = temp.replace(" ", "")
                    elif char != " ":
                        hit_space = False

                    outfile.write(temp)
            
        # write out to csv
        base = os.path.splitext(file)[0]
        output = base + ".csv"
        with open(file, encoding="ISO-8859-1") as input_file, open(output, 'w') as output_file: # or 'wb' if on python2
            writer = csv.writer(output_file)

            i = 0
            for line in input_file:
                writer.writerow([i] + line.rstrip().split(' '))