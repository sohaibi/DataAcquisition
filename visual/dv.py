import plotly.graph_objects as go
import car_data

figure = go.Figure()

#Used to turn on and off graphs
data_1 = True
data_2 = True
data_3 = True
data_4 = True
data_5 = True

#Function to transfer data into array
def get_list(column_name):
    ax = (car_data.get_var(column_name))
    data = []
    for itm in ax:
        data.append(itm)
    return data

# Add traces
if data_1 == True:
    figure.add_trace(go.Scatter(x=get_list("t"), y=get_list("pos_x"), mode='lines+markers', name='data_1'))
if data_2 == True:
    figure.add_trace(go.Scatter(x=get_list("t"), y=get_list("pos_y"), mode='lines+markers', name='data_2'))
if data_3 == True:
    figure.add_trace(go.Scatter(x=get_list("t"), y=get_list("x"), mode='lines+markers', name='data_3'))
if data_4 == True:
    figure.add_trace(go.Scatter(x=get_list("t"), y=get_list("v"), mode='lines+markers', name='data_4'))
if data_5 == True:
    figure.add_trace(go.Scatter(x=get_list("t"), y=get_list("a_long"), mode='lines+markers', name='data_5'))

figure.show()