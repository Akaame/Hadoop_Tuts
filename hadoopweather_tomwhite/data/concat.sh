#!/usr/bin/env bash

g_target_file="weather_data"

function create_folder {
	if [ -d "$1" ]; then
		rm -rf "$1";
	fi
	mkdir "$1"
		
}

function main {
	create_folder "$g_target_file"
	local target_file="data.txt"
	for file in ncdc_data/*;
	do
		gunzip -c "$file"  >> "$g_target_file/$target_file"
	
	done
}

main
