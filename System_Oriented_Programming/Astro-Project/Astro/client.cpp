#include "client.h"
#include "WiFi.h"

// Prints wether the wifi is correctly connected or not and attempts to reconnect
wl_status_t	handle_wifi(const char *ssid, const char *password)
{
	wl_status_t wifi_Status = WiFi.status();
	if (wifi_Status != WL_CONNECTED)
	{
		Serial.println("[ERR] Lost WiFi connection, reconnecting...");
		if (connect_Wifi(ssid, password))
			Serial.println("[OK] WiFi reconnected");
		else 
			Serial.println("[ERR] unable to reconnect");
	}
	wifi_Status = WiFi.status();
	if (wifi_Status == WL_CONNECTED)
		Serial.println(WiFi.localIP());
	return (wifi_Status);
}

// Establish connection to the specified network until success. Tries 10 times before giving up
bool	connect_Wifi(const char *ssid, const char *password)
{
	WiFi.disconnect();
	Serial.print("Connecting to ");
	Serial.println(ssid);
	delay(1000);
	
	WiFi.begin(ssid, password);
	wl_status_t wifi_Status = WiFi.status();
	for (int n_trials = 0;wifi_Status != WL_CONNECTED && n_trials < 10; n_trials++)
	{
		wifi_Status = WiFi.status();
		print_wifi_status(wifi_Status);
		delay(1000);
	}
	if (wifi_Status == WL_CONNECTED)
	{
		Serial.print("IP address: ");
		Serial.println(WiFi.localIP());
		return true;
	}
	else
	{
		Serial.println("[ERR] unable to connect Wifi");
		return false;
	}
}

// Basically prints the wifi status
void	print_wifi_status(wl_status_t wifi_Status)
{
	switch(wifi_Status)
	{
		case WL_NO_SSID_AVAIL:
				Serial.println("[ERR] WIFI SSID not available");
				break;
		case WL_CONNECT_FAILED:
				Serial.println("[ERR] WIFI Connection failed");
				break;
		case WL_CONNECTION_LOST:
				Serial.println("[ERR] WIFI Connection lost");
				break;
		case WL_DISCONNECTED:
				Serial.println("[STATE] WiFi disconnected");
				break;
		case WL_IDLE_STATUS:
				Serial.println("[STATE] WiFi idle status");
				break;
		case WL_SCAN_COMPLETED:
				Serial.println("[OK] WiFi scan completed");
				break;
		case WL_CONNECTED:
				Serial.println("[OK] WiFi connected");
				break;
		default:
				Serial.println("[ERR] WIFI unknown Status");
				break;
	}
}

// Prints all the data to the client
void	print_client_data(WiFiClient client, Datastructure *data)
{
	// Plot only the last MAX_TIME points in the jsons table
	// PS: If the following while doesn't work, try this to be sure that there is no other error: client.print("[1, 3],[2, 2.5],[3, 3],[4, 4],[5, 204],[6, 3]");
	for (int count = 0; count <= MAX_TIME; count++)
	{
		// Calculate the position in the jsons table that we want to plot
		int pos = data->index_jsons - 1 - count;

		// If we have a negative position it could mean that we need to go to the top of our jsons table (we begin at the bottom if we have reached MAX_JSONS)
		if (pos < 0)
			pos += MAX_JSONS;

		// Convert the data at pos in a string
		String str = String(data->table_jsons[pos]);

		// If we have only have between 0 and MAX_TIME data we need to drop them, so that the plot works
		if (str != "")
			client.print(str + ",");
	}
}

// Prints the client header on the server
void	print_client_header(WiFiClient client)
{
	client.println("HTTP/1.1 200 OK");
	client.println("Content-Type: text/html");
	client.println("Connection: close");
	client.println("Refresh: 5");

	// IMPORTANT: DON'T DELETE THIS LINE (Don't ask me why, ask HTML)
	client.println();
	client.println("<!DOCTYPE html>");
	client.println("<html>");
	client.println("<head>");

	// Title of website
	client.println("<title>Project</title>");

	// 2 scripts to be able to show a 2D plot with Illuminance vs. Time
	client.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
	client.println("<script type=\"text/javascript\">");

	// Use google functions to be able to plot
	client.println("google.charts.load(\"current\", {packages:[\"corechart\"]});");
	client.println("google.charts.setOnLoadCallback(drawChart);");

	// Send the data in following form: [['X','Y'],[1,1],[2,4],[3,2]]
	// Start with data variables ['T', 'I']
	client.print("function drawChart(){var data = google.visualization.arrayToDataTable([['T', 'I'],");
}

// Prints the client tail on the server
void	print_client_tail(WiFiClient client, float lux)
{
	// End the data with "]);"
	client.println("]);");

	// Options for the plot: no legend, blue points with size 5, define vertical and horizontal axis with the corresponding label title
	client.println("var options={legend:'none',colors:[\"blue\"],pointSize:5,series:{0:{targetAxisIndex:0}},vAxes:{0:{title:'Illuminace [lx]'}},hAxes:{0:{title:'Time [s]'}}};");
	
	// The plot is a lineplot and we call this object "chart_div" in this html file
	client.println("var chart = new google.visualization.LineChart(document.getElementById('chart_div'));");
	
	// Plot the data
	client.println("chart.draw(data, options);}");
	client.println("</script>");
	client.println("</head>");
	client.println("<body>");

	// Print the title of the page and the project description
	client.println("<h1> Project 3PHY1INF </h1>");
	client.println("This project has as goal to measure the light intensity of the sky in the nigth and deduce the air pollution for astronomical observations.<br><br>");
	
	// Print the last input of Illuminance in lux
	client.print("Last input of illuminance in lux is: ");
	client.print(lux);
	client.println(" lux<br><br>");
	
	// Plot the graph with width and height
	client.println("<div id=\"chart_div\" style=\"width: 900px; height: 500px;\"></div>");

	// Adding the authors of the project
	client.println("Authors of the project: Vincent Glauser, Sylvain Lott, Tristan Henchoz & Katja Sophia Moos");
	client.println("</body>");
	client.println("</html>");
}
