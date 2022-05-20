from random import choice
import random
import string

CASOS = 100000
def crearPruebas():

    f = open("in.in", "w+")
    f.write(repr(CASOS)+"\n")
    o = open("out.out", "w+")
    # cantidad casos
    for i in range(0, CASOS):
        cadenaOriginal = "".join(random.choices(string.ascii_lowercase, k=random.randint(1, 100)))
        o.write(cadenaOriginal+ " ")
        newWord = cadenaOriginal

        while (cadenaOriginal != ""):
            letra = choice(cadenaOriginal)
            o.write(letra)
            cadenaSinLetra = cadenaOriginal.replace(letra, "")
            newWord += cadenaSinLetra

            cadenaOriginal = cadenaSinLetra
        o.write("\n")
        f.write(newWord+ "\n")

    f.close()
    o.close()


crearPruebas()
            
            
    



# cadenaOriginal = "anekmdnesnakdnak"
# newWord = cadenaOriginal



# while (cadenaOriginal != ""):

#     letra = choice(cadenaOriginal)

#     cadenaSinLetra = cadenaOriginal.replace(letra, "")

#     newWord += cadenaSinLetra

#     cadenaOriginal = cadenaSinLetra

# print(newWord)
