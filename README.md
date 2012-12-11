# Bundle Protocol Framework (BPF)

## General
### About
This is a project made by (or partially by) WSN-Team 2012 in the course CSD, which is part of the [Technology Transfer Alliance](http://ttaportal.org/).
### Purpose
One of the goals of the project is to be able to send data upstream from rural areas and to do this DTN communication has been used.

We wanted something that could be used in all devices for handling the DTN communication. That is why we created a platform-independent framework for Delay Tolerant Network (DTN) communication. This project will provide this as a framework for different platforms. 
### Description
This project is a continuation of the [Bytewalla project](https://code.google.com/p/dtn-bytewalla/). This project differs from Bytewalla mainly because we made a framework which is platform-independent based on the code provided by the Bytewalla team. Also a lot of cleaning up and bug fixing has been done (believe us...).

The BPF is a framework used in all our DTN devices as a base for all DTN communication. The framework cannot work on its own. It needs to be implemented with a application/service, which is device specific. To implement your own application/service using the BPF you will need to look into the interfaces defined within the `se.kth.ssvl.tslab.wsn.general.bpf`. However there are already services implementing the BPF in our [organization page](https://github.com/WSN-2012).


## Build & Install

### Prerequisites
You will need to have ant to compile this in an easy way. To get ant look into how to install it on your platform.

### Buidling
The BPF should be built into a jar which should be imported into the application/service implementing it. 
Follow the below steps to build it.

1.  `git clone https://github.com/WSN-2012/BPF.git`
2.  `cd BPF`
3.  `ant`
