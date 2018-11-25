﻿using System;
using System.Collections.Generic;
using System.Text;

namespace DataLayer.Model
{
    public class Location
    {
        public Location(double lat, double lon, string name)
        {
            Lat = lat;
            Lon = lon;
            Name = name;
        }
        public int Id { get; set; }
        public double Lat { get; set; }
        public double Lon { get; set; }
        public string Name { get; set; }

    }
}
