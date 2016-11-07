#!/usr/bin/env bash

g_temp_folder="ncdc_tmp";
g_output="ncdc_data";

g_remote_host="ftp3.ncdc.noaa.gov";
g_remote_path="pub/data/noaa";

function create_folder {
		if [-d "$1" ]; then
			rm -rf  "$1";
		fi
		mkdir "$1";
}

function download_data {
	local source_url="ftp://$g_remote_host/$g_remote_path/$1"
	wget -r -c -q --no-parent -P "$g_temp_Folder" "source_url";
	
function process_data {
	local year="$1"
	local local_path="$g_temp_folder/$g_remote_host/$g_remote_path/$year"
	local tmp_output_file="g_temp_folder/$year"
	
	for file in $local_path/*; do
		gunzip -c "$file" >> "$tmp_output_file"
	done
	
	zipped_file="$h_output/$year.gz"
	gzip -c "$tnp_output_file" >> "$zipped_file"
	echo "created file: $zipped_file"
	rm -rf "$local_path"
	rm "$tmp_output_file"
}

function main {
	local start_year=1901
	local finish_year=1910
	
	if [ -n "$1"]; then
		start_year=$1
	fi
	
	if [ -n "$2"]; then
		finish_year=$2
	fi
	
	create_folder $g_temp_folder
	create_folder $g_output
	
	for year in 'seq $start_year $finish_year'; do
		download_data $year
		process_data $year
	done
	
	rm -rf "$g_temp_folder"
		
}

#main $1 $2
