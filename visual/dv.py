import plotly.graph_objects as go

figure = go.Figure()

#Used to turn on and off graphs
data_1 = True
data_2 = True
data_3 = True
data_4 = True
data_5 = True


# Add traces
if data_1 == True:
    figure.add_trace(go.Scatter(x=[0, 2, 4, 6], y=[0, 1, 2, 3], mode='lines+markers', name='data_1'))
if data_2 == True:
    figure.add_trace(go.Scatter(x=[0, 2, 4, 6], y=[0, 4, 5, 6], mode='lines+markers', name='data_2'))
if data_3 == True:
    figure.add_trace(go.Scatter(x=[0, 2, 4, 6], y=[0, 7, 7.5, 8], mode='lines+markers', name='data_3'))
if data_4 == True:
    figure.add_trace(go.Scatter(x=[0, 2, 4, 6], y=[0, 2, 7, 9], mode='lines+markers', name='data_4'))
if data_5 == True:
    figure.add_trace(go.Scatter(x=[0, 2, 4, 6], y=[0, 2, 9, 11], mode='lines+markers', name='data_5'))

figure.show()