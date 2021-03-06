Packaging
=========

This documentation is relevant to those who need to build packages of PDSView
for specific platforms. If you're looking to download and use PDSView, see the
``README.rst`` file instead.


Requirements
------------

Note that to create a platform-specific package, you need to do so on the
intended platform itself.  In other words, you can't create the Windows
package on a Linux host, nor the macOS package on a Windows host.

Regardless of platform, the requirements are the same. First, install Python
2.7 with Tkinter support. Then install PyQt4 into the Python 2.7 installation.
Then, make a virtualenv from *that* installation, letting the virtualenv
have access to PyQt4 by passing ``--system-site-packages``::

    virtualenv --system-site-packages /tmp/mypython  (macOS, Linux, other Unix-like)
    virtualenv.exe --system-site-packages \tmp\mypython  (Windows)

(If your Python 2.7 installation didn't come with ``vitualenv``, you may have
to install it separately.) Inside that virutalenv, install ``matplotlib``,
and ``seaborn``. On macOS, Linux, or other Unix-like systems, that looks
something like this::

    cd /tmp/mypython
    bin/pip install matplotlib seaborn

While on Windows, it might go like this::

    cd \tmp\mypython
    Scripts\pip.exe install matplotlib seaborn


Building Out
------------

Building the platform-specific installers leverages Buildout_.  The Buildout
bootstrapper script, ``boostrap.py`` is included with the PDSView source.
Simply do the following (adjusting paths and platform-specifics as needed):

1. Use the Python from your vitualenv to bootstrap:
   ``/tmp/mypython/bin/python2.7 bootstrap.py``
2. Build out: ``bin/buildout``

Then, run the platform-specific installation:

For Windows
    ``bin/buildout install windows``. This will create
    ``dist/PDSView.exe`` which Windows users can double-click to run. Or do
    ``bin/buildout install windows-dir`` to create
    ``dist/PDSView``, which contains a hundred different support files plus
    the ``PDSView.exe`` executable. This version launches faster but comes
    as a full directory of files, while the single ``.exe`` launches much
    more slowly but ships more conveniently.
For macOS
    ``bin/buildout install macos``. This will create
    ``dist/PDSView.app`` which macOS users can double-click to run.
For Linux
    ``bin/buildout install linux``. This will create
    ``dist/PDSView`` which Linux users can run.

You can optionally compress the target files/directories prior to distribution
if you wish.


.. _Buildout: https://buildout.org/
