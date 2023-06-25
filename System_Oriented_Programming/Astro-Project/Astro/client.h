#ifndef WIFI_H
#define WIFI_H

#include "Datastructure.h"
#include "WiFi.h"

wl_status_t	handle_wifi(const char *ssid, const char *password);
bool		connect_Wifi(const char *ssid, const char *password);
void		print_wifi_status(wl_status_t wifi_Status);
void		print_client_header(WiFiClient client);
void		print_client_tail(WiFiClient client, float lux);
void		print_client_data(WiFiClient client, Datastructure *data);

#endif
