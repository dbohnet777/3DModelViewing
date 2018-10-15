#version 430

out vec4 color;

void main(void)
{ 	
	if (gl_VertexID == 0){
		color = vec4(0.31, 0.0, 0.51, 1.0);	//purplish color vertex
		gl_Position = vec4( 0.25, -0.25, 0.0, 1.0);
	}
	else if (gl_VertexID == 1){
		color = vec4(1.0, 0.0, 1.0, 1.0);	//purple vertex
		gl_Position = vec4( -0.25, -0.25, 0.0, 1.0);
	}
	else{
		color = vec4(0.0, 0.0, 1.0, 1.0);	//green vertex
		gl_Position = vec4( 0.25, 0.25, 0.0, 1.0);
	}
}