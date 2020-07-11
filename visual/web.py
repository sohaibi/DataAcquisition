import dash
import dash_html_components as html
import dash_core_components as dcc
from dash.dependencies import Input, Output
from graph_page import GraphPage

app = dash.Dash(__name__)
graph_list = [GraphPage("Settings Page"), GraphPage("Graph 1")]
tab_list = [dcc.Tab(label="Settings", value="tab_0"),
            dcc.Tab(label="Graph 1", value="tab_1")]

def add_graph(name = None):
    cur_num = len(graph_list)
    name = f"Graph {cur_num}" if name is None else name
    graph_list.append(GraphPage(name))
    tab_list.append(dcc.Tab(label=name, value=f"tab_{cur_num}"))


app.layout = html.Div([
    dcc.Tabs(id="graph-tabs", value="tab_0"),
    html.Button("Add Graph", id="button"),
    html.Div(id='tab-display')
],id='app-container')


@app.callback(Output('graph-tabs', 'children'),
            [Input('button', 'n_clicks')])
def update_dabs(n_clicks):
    if n_clicks is not None:
        add_graph()
    return tab_list


@app.callback(Output('tab-display', 'children'),
            [Input('graph-tabs', 'value')])
def render_content(tab):
    tab_num = int(tab[4])
    return html.Div([
        html.H3(graph_list[tab_num].page_name)
    ])

app.run_server(debug=True)