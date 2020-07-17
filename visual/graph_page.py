from dv import Graph

class GraphPage:

    def __init__(self, page_name = None):
        self.graph = Graph()
        self.page_name = page_name
        self.var_list = self.graph.titles

    
