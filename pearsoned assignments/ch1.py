# -*- coding: UTF-8 -*-
'''Dato un array di n elementi con ripetizioni, sia k il numero di
elementi distinti in esso contenuti. Progettare e analizzare
un algoritmo che restituisca un array di k elementi contenente
una e una sola volta gli elementi distinti dell’array originale.'''


def select_distincts(a_list):
    '''Il costo dell’algoritmo e` dominato dal costo dell’algoritmo Sort \
    utilizzato per l’ordinamento. Come si vedra` questo puo` essere fatto \
    in tempo O(nlogn)'''
    a_list.sort()
    result = [a_list[0]]
    j = 1
    for i in range(1, len(a_list)):
        if (a_list[i] != a_list[i - 1]):
            result[j] = a_list[i]
            j += 1
    return result


'''Descrivere un algoritmo che, dati due array a e b di n e m elementi,
rispettivamente, con m <= n, determini se esiste un segmento di a
uguale a b in tempo O(nm).'''


def sub_list(list_a, list_b):
    n = len(list_a)
    m = len(list_b)
    for i in range(n - m + 1):
        j = 0
        while j < m and list_a[i + j] == list_b[j]:
            j += 1
        if j == m:
            return i
    return None


'''Scrivete il codice per realizzare la conversione e l’interprete per le
espressioni polacche inverse. Usare uno spazio bianco per separare variabili
e costanti.'''


def polish_interpreter(the_list):
    '''the_list contains the expression'''
    stack = []
    for i in range(len(the_list)):
        if the_list[i] is '+' or the_list[i] is 'x':
            op1 = stack.pop()
            op2 = stack.pop()
            if (the_list[i] == '+'):
                stack.extend([op1 + op2])
            else:
                stack.extend([op1 * op2])
        else:
            stack.extend([the_list[i]])
    return stack.pop()


'''Dato un array ordinato contenentene l ementi di tipo intero.Progettare
un algoritmo che data una chiave k restituisce il numero di occorrenze
di k nell’array. L’algoritmo deve avere complessita` O(logn)'''


def count(a_list, sx, dx, k):
    '''La soluzione e` una variante della ricerca binaria: deve evitare di
    effettuare chiamate ricorsive su segmenti S dell’array che (a) contengono
    chiavi k e (b) l’elemento nell’array che precede S e quello che succede a S
    nell’array sono uguali a k.'''
    if sx > dx:
        return 0
    cx = (sx + dx) / 2
    if k < a_list[cx]:
        return count(a_list, sx, cx - 1)
    elif k > a_list[cx]:
        return count(a_list, cx + 1, dx)
    else:
        c = 1
        if k == a_list[sx]:
            c += cx - sx
        else:
            c += count(a_list, sx, cx - 1)
        if k == a_list[dx]:
            c += dx - cx
        else:
            c += c + count(a_list, cx + 1, dx)
        return c


'''Modificare il codice ricorsivo della ricerca binaria in modo che, nel caso \
che l’array a memorizzi un multi-insieme ordinato dove gli elementi possono \
comparire piu` volte, restituisca la posizione piu` a destra dell’elemento \
cercato.'''


def recursive_bin_search(a_list, value, sx, dx):
    '''L’osservazione da fare e`che l’operazione centro = (sinistra+destra)/2 \
    tronca e, quindi, occorre arrotondare con centro = (sinistra+destra+1)/2: \
    a questo punto soltanto il codice simmetrico a quello della ricerca \
    binaria puo` funzionare (altrimenti va in loop!).'''
    if sx == dx:
        if value == a_list[sx]:
            return sx
        # else:
        return None
    center = (sx + dx + 1)/2
    if value >= a_list[center]:
        return recursive_bin_search(a_list, value, center, dx)
    # else:
    return recursive_bin_search(a_list, value, sx, center)


'''Estendere la QuickSelect randomizzata in modo da trovare gli elementi di \
rango compreso tra r1 e r2, dove r1 < r2. Studiare la complessita` al caso \
medio dell’algortimo proposto.'''


def quick_select(a_list, sx, r1, r2, dx):
    '''L’estensione tiene conto del fatto che, anche quando troviamo un \
    elemen- to del rango compreso nell’intervallo, occorre comunque continuare\
    con la ricorsione.
    Per l’analisi, a parte il confronto nella linea 7, il \
    costo e` come la QuickSelect randomizzata, quindi O(n) al caso medio. \
    A questo costo va aggiunto quello della linea 7, che e` O(n) al caso \
    pessimo. Il costo medio totale rimane quindi O(n).'''
    if sx == dx:
        return a_list[sx]
    pivot = sx + (dx - sx) * random()
    rango = distribution(a, sx, pivot, dx)
    if r1 - 1 <= rango and rango <= r2 - 1:
        return a[rango]
    if r1 - 1 < rango:
        return quick_select(a, sx, r1, r2, rango-1)
    if rango < r2 - 1:
        return quick_select(a, rango + 1, r1, r2, dx)


