This is my solar system model

The controls are simple. Left and Right pan the view left and right. Up and doown control the pitch.
To travel to a planet you must press left shift and then enter the planet you would like to travel
to by entering its corresponding number e.g. earth is 3 mars is 4. The order of the planets is:
Mercury
Mars
Earth
Venus
Jupiter
Saturn
Uranus
Neptune
Pluto

The entire solar system is to scale both in distance and object sizes. The exception to this is the 
placement of the moons. If I would have followed the scale they would have been too far to find :)
I implemented one interesting feature which I dubbed the Star Trek effect. When a "ship" travels at fast
speeds, stars are often shown as lines rather than dots and I wanted to experiment with it. To do this
I had the sky sphere scaled accordingly while travelling to a planet and then scale back to normal size
when you arrive at the planet. This can be turned off by commenting out line 315 in the main class.
When you are not travelling toward a planet or have just arrived at a planet, this places you in freeflight mode
which gives you the additional option of propelling forward with space. Once a course has been laid in though
free flight mode is turned off until you arrive. You may look around though while you are travelling.