binary_tree = {'A': ['B', 'C'],
               'B': ['D', 'E'],
               'C': ['F', 'G'],
               'D': ['H', None],
               'E': [None, None],
               'F': [None, None],
               'G': [None, None],
               'H': [None, None],
               }


def left_son(node):
    return binary_tree[node][0]


def right_son(node):
    return binary_tree[node][1]


def bfs_visit(start_node):
    queue = [start_node]
    while queue:
        node = queue.pop(0)
        if node:
            # visit the node
            print 'Node ' + str(node) + ': ' + str(binary_tree[node])
            queue.append(left_son(node))
            queue.append(right_son(node))


def dfs_visit_iterative(start_node):
    stack = [start_node]
    while stack:
        node = stack.pop()
        if node:
            # visit the node
            print 'Node ' + str(node) + ': ' + str(binary_tree[node])
            stack.append(left_son(node))
            stack.append(right_son(node))


def dfs_visit_recursive(node):
    if not node:
        return
    # visit the node
    print 'Node ' + str(node) + ': ' + str(binary_tree[node])
    dfs_visit_recursive(left_son(node))
    dfs_visit_recursive(right_son(node))
