firewall {
    all-ping enable
    broadcast-ping disable
    ipv6-name LAN-TO-LOCAL-6 {
        default-action drop
        enable-default-log
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
        rule 100 {
            action accept
            protocol ipv6-icmp
        }
        rule 200 {
            action accept
            description "Allow HTTP/HTTPS"
            destination {
                port 80,443
            }
            protocol tcp
        }
        rule 600 {
            action accept
            description "Allow DNS"
            destination {
                port 53
            }
            protocol tcp_udp
        }
        rule 700 {
            action accept
            description "Allow DHCP"
            destination {
                port 67,68
            }
            protocol udp
        }
        rule 800 {
            action accept
            description "Allow SSH"
            destination {
                port 22
            }
            protocol tcp
        }
    }
    ipv6-name LAN-TO-WAN-6 {
        default-action accept
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
        rule 100 {
            action accept
            protocol ipv6-icmp
        }
    }
    ipv6-name LOCAL-TO-LAN-6 {
        default-action accept
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
        rule 100 {
            action accept
            protocol ipv6-icmp
        }
    }
    ipv6-name LOCAL-TO-WAN-6 {
        default-action accept
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
        rule 100 {
            action accept
            protocol ipv6-icmp
        }
    }
    ipv6-name WAN-TO-LAN-6 {
        default-action drop
        enable-default-log
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
        rule 100 {
            action accept
            protocol ipv6-icmp
        }
        rule 110 {
            action accept
            description NAS-WEB
            destination {
                address 2a02:xxxx:xxx:xxx1:20c:xxxx:fe0e:xxx
                port 80,443,5000,5001
            }
            protocol tcp
        }
    }
    ipv6-name WAN-TO-LOCAL-6 {
        default-action drop
        enable-default-log
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action accept
            protocol ipv6-icmp
        }
        rule 3 {
            action accept
            destination {
                port 546
            }
            protocol udp
            source {
                port 547
            }
        }
        rule 100 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
    }
    ipv6-receive-redirects disable
    ipv6-src-route disable
    ip-src-route disable
    log-martians enable
    name LAN-TO-LOCAL {
        default-action drop
        enable-default-log
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
        rule 100 {
            action accept
            protocol icmp
        }
        rule 200 {
            action accept
            description "Allow HTTP/HTTPS"
            destination {
                port 80,443
            }
            protocol tcp
        }
        rule 600 {
            action accept
            description "Allow DNS"
            destination {
                port 53
            }
            protocol tcp_udp
        }
        rule 700 {
            action accept
            description "Allow DHCP"
            destination {
                port 67,68
            }
            protocol udp
        }
        rule 800 {
            action accept
            description "Allow SSH"
            destination {
                port 22
            }
            protocol tcp
        }
    }
    name LAN-TO-WAN {
        default-action accept
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
    }
    name LOCAL-TO-LAN {
        default-action accept
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
    }
    name LOCAL-TO-WAN {
        default-action accept
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
    }
    name WAN-TO-LAN {
        default-action drop
        enable-default-log
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
        rule 3 {
            action accept
            protocol icmp
        }
    }
    name WAN-TO-LOCAL {
        default-action drop
        enable-default-log
        rule 1 {
            action accept
            state {
                established enable
                related enable
            }
        }
        rule 2 {
            action drop
            log enable
            state {
                invalid enable
            }
        }
        rule 3 {
            action accept
            protocol icmp
        }
    }
    receive-redirects disable
    send-redirects enable
    source-validation disable
    syn-cookies enable
}
interfaces {
    ethernet eth0 {
        address 192.168.1.1/24
        description LAN1
        duplex auto
        speed auto
    }
    ethernet eth1 {
        address dhcp
        description WAN
        dhcp-options {
            client-option "send vendor-class-identifier &quot;neufbox_NB6VAC-FXC-r0_NB6VAC-MAIN-R4.0.35_NB6VAC-XDSL-A2pv6F039p&quot;;"
            default-route update
            default-route-distance 210
            name-server update
        }
        dhcpv6-pd {
            duid 00030001e45dxxxxxxxx
            pd 1 {
                interface eth0 {
                    host-address ::1
                    prefix-id 1
                    service slaac
                }
                interface eth2 {
                    host-address ::1
                    prefix-id 2
                    service slaac
                }
                prefix-length /56
            }
            rapid-commit enable
        }
        duplex auto
        ipv6 {
            address {
                autoconf
            }
            dup-addr-detect-transmits 1
        }
        mac e4:5d:51:xx:xx:xx
        speed auto
    }
    ethernet eth2 {
        address 192.168.2.1/24
        description LAN2
        duplex auto
        speed auto
    }
    loopback lo {
    }
}
port-forward {
    auto-firewall enable
    hairpin-nat enable
    lan-interface eth0
    lan-interface eth2
    rule 1 {
        description RDP-TO-WORKSTATION
        forward-to {
            address 192.168.1.2
            port 3389
        }
        original-port 3389
        protocol tcp_udp
    }
    rule 2 {
        description NAS-5001
        forward-to {
            address 192.168.1.11
            port 5001
        }
        original-port 5001
        protocol tcp
    }
    rule 3 {
        description NAS-443
        forward-to {
            address 192.168.1.11
            port 5001
        }
        original-port 443
        protocol tcp
    }
    rule 4 {
        description NAS-80
        forward-to {
            address 192.168.1.11
            port 80
        }
        original-port 80
        protocol tcp_udp
    }
    rule 5 {
        description NAS-16881
        forward-to {
            address 192.168.1.11
            port 16881
        }
        original-port 16881
        protocol tcp_udp
    }
    wan-interface eth1
}
service {
    dhcp-server {
        disabled false
        hostfile-update disable
        shared-network-name LAN1 {
            authoritative disable
            subnet 192.168.1.0/24 {
                default-router 192.168.1.1
                dns-server 192.168.1.1
                lease 86400
                start 192.168.1.150 {
                    stop 192.168.1.175
                }
                static-mapping EUFRPC09839V {
                    ip-address 192.168.1.3
                    mac-address 18:5e:0f:xx:xx:xx
                }
            }
        }
        shared-network-name LAN2 {
            authoritative disable
            subnet 192.168.2.0/24 {
                default-router 192.168.2.1
                dns-server 192.168.2.1
                lease 86400
                start 192.168.2.150 {
                    stop 192.168.2.175
                }
            }
        }
        static-arp disable
        use-dnsmasq disable
    }
    dns {
        forwarding {
            cache-size 500
            listen-on eth0
            listen-on eth2
        }
    }
    gui {
        http-port 80
        https-port 443
        older-ciphers enable
    }
    nat {
        rule 5010 {
            description "Masquerade for WAN"
            outbound-interface eth1
            type masquerade
        }
    }
    ssh {
        port 22
        protocol-version v2
    }
}
system {
    host-name ubnt
    login {
        user ubnt {
            authentication {
                encrypted-password $6$Y/0giZkb4.JR$WoLvl8zIh4dGjn5/zRsPvxxxxxxxxxxxxxxxxxxxxxxxxxeViqXQV4BhxX1HMQbgHpAzYU.Fpxg7K8/
                plaintext-password ""
            }
            full-name ""
            level admin
        }
    }
    ntp {
        server time1.google.com {
        }
        server time2.google.com {
        }
        server time3.google.com {
        }
        server time4.google.com {
        }
    }
    offload {
        hwnat disable
        ipsec enable
        ipv4 {
            forwarding enable
            gre enable
            vlan enable
        }
        ipv6 {
            forwarding enable
            vlan enable
        }
    }
    syslog {
        global {
            facility all {
                level notice
            }
            facility protocols {
                level debug
            }
        }
    }
    time-zone Europe/Paris
}
zone-policy {
    zone LAN {
        default-action drop
        from LOCAL {
            firewall {
                ipv6-name LOCAL-TO-LAN-6
                name LOCAL-TO-LAN
            }
        }
        from WAN {
            firewall {
                ipv6-name WAN-TO-LAN-6
                name WAN-TO-LAN
            }
        }
        interface eth0
        interface eth2
    }
    zone LOCAL {
        default-action drop
        from LAN {
            firewall {
                ipv6-name LAN-TO-LOCAL-6
                name LAN-TO-LOCAL
            }
        }
        from WAN {
            firewall {
                ipv6-name WAN-TO-LOCAL-6
                name WAN-TO-LOCAL
            }
        }
        local-zone
    }
    zone WAN {
        default-action drop
        from LAN {
            firewall {
                ipv6-name LAN-TO-WAN-6
                name LAN-TO-WAN
            }
        }
        from LOCAL {
            firewall {
                ipv6-name LOCAL-TO-WAN-6
                name LOCAL-TO-WAN
            }
        }
        interface eth1
    }
}
