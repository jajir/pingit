# PingIt


Simple tool that show server latency


## PingIt implementation detail

Following text describe operation under the hood of ping operation:

Test whether that address is reachable. Best effort is made by the
implementation to try to reach the host, but firewalls and server
configuration may block requests resulting in a unreachable status while
some specific ports may be accessible. A typical implementation will use
ICMP ECHO REQUESTs if the privilege can be obtained, otherwise it will
try to establish a TCP connection on port 7 (Echo) of the destination
host.



## Examples

Following example ping to `google.com` 10 times. It has to be started with root rights.

```
sudo java -jar ./pingit.jar --host google.com
response from google.com was successfully returned in 3127 ms
response from google.com was successfully returned in 9 ms
response from google.com was successfully returned in 11 ms
response from google.com was successfully returned in 9 ms
response from google.com was successfully returned in 10 ms
response from google.com was successfully returned in 13 ms
response from google.com was successfully returned in 8 ms
response from google.com was successfully returned in 10 ms
response from google.com was successfully returned in 12 ms
response from google.com was successfully returned in 13 ms
```

Next example perform just 2 pings:

```
sudo java -jar ./pingit.jar --host google.com --count 2
response from google.com was successfully returned in 24 ms
response from google.com was successfully returned in 12 ms
```

Help could be obtained like this:

```
java -jar ./pingit.jar --help                          
usage: java -jar testit.jar
Simple tool that show server latency by pinging it
 -c,--count <arg>   How many times should be ping performed. When this
                    option is not used, than default value 10 is used. If
                    there is no count argument than program will run until
                    interrupted
 -h,--help          display help
    --host <arg>    required host name, e.g. google.com
```
