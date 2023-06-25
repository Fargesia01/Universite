- The group 3PHY1INF consists of:
    - Vincent Glauser
    - Katja Sophia Moos
    - Sylvain Lott
    - Tristan Henchoz

- Project Idea:
    Programming a websensor which can measure the light intensity of the sky in the night and deduce the air pollution for astronomical observations. This should be possible to do by a general light sensor. The sensor would be placed inside a black box with an opening directed to the sky to prevent light pollution from the sides.

- Sensor:
    We are using the VEML7700 sensors from the LearningLab. The connection with the AtomLite worked well and we can easily read the values which are measured by the VEML7700.

- Box:
    The box was printed using the printer from the robotic lab. We used a template taken from printables.com that we modified to our needs.

- Pipeline:
    Our pipeline has 3 steps and uses a custom container (built from the Dockerfile provided here):
    - 1. Checks the Makefile and wether everything compiles
    - 2. Launches the test file built in step 1 and checks wether all tests went fine
    - 3. Checks wether there are any memory leaks after destroying our datastructure
