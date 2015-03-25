def bubble_sort(alist):
    for passnum in range(len(alist) - 1, 0, -1):
        for i in range(passnum):
            if alist[i] > alist[i + 1]:
                temp = alist[i]
                alist[i] = alist[i + 1]
                alist[i + 1] = temp


def selection_sort(alist):
    for fill_slot in range(len(alist) - 1, 0, -1):
        position_of_max = 0
        for location in range(1, fill_slot + 1):
            if alist[location] > alist[position_of_max]:
                position_of_max = location

        temp = alist[fill_slot]
        alist[fill_slot] = alist[position_of_max]
        alist[position_of_max] = temp


def insertion_sort(alist):
    for index in range(1, len(alist)):

        current_value = alist[index]
        position = index

        while position > 0 and alist[position - 1] > current_value:
            alist[position] = alist[position - 1]
            position = position - 1

        alist[position] = current_value


def merge_sort(alist):
    if len(alist) > 1:
        mid = len(alist) // 2
        left_half = alist[:mid]
        right_half = alist[mid:]

        merge_sort(left_half)
        merge_sort(right_half)

        i = 0
        j = 0
        k = 0
        while i < len(left_half) and j < len(right_half):
            if left_half[i] < right_half[j]:
                alist[k] = left_half[i]
                i = i + 1
            else:
                alist[k] = right_half[j]
                j = j + 1
            k = k + 1

        while i < len(left_half):
            alist[k] = left_half[i]
            i = i + 1
            k = k + 1

        while j < len(right_half):
            alist[k] = right_half[j]
            j = j + 1
            k = k + 1


def quick_sort(alist):
    quick_sort_helper(alist, 0, len(alist) - 1)


def quick_sort_helper(alist, first, last):
    if first < last:

        split_point = partition(alist, first, last)

        quick_sort_helper(alist, first, split_point - 1)
        quick_sort_helper(alist, split_point + 1, last)


def partition(alist, first, last):
    pivot_value = alist[first]

    left_mark = first + 1
    right_mark = last

    done = False
    while not done:

        while left_mark <= right_mark and \
                alist[left_mark] <= pivot_value:
            left_mark = left_mark + 1

        while alist[right_mark] >= pivot_value and \
                right_mark >= left_mark:
            right_mark = right_mark - 1

        if right_mark < left_mark:
            done = True
        else:
            temp = alist[left_mark]
            alist[left_mark] = alist[right_mark]
            alist[right_mark] = temp

    temp = alist[first]
    alist[first] = alist[right_mark]
    alist[right_mark] = temp

    return right_mark
