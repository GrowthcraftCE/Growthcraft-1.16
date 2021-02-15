import sys

def main():

  colors = [ "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black" ]

  sourceFile = sys.argv[1]
  fileIn = open( sourceFile, "rt")

  for color in colors:
    with open( sourceFile, "rt") as fileIn:
      targetFile = sourceFile.replace("white", color)
      with open(targetFile, "wt") as fileOut:
        for line in fileIn:
          newLine = line.replace("white", color)
          print newLine
          fileOut.write(newLine)

if __name__ == '__main__':
  main()

