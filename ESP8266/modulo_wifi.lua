-- Setando modo Wifi para estação e Ponto de Acesso
wifi.setmode(wifi.STATIONAP)

-- Access Point com senha
cfg={}
--definir nome da rede (SSID)
cfg.ssid="testeWiFi"
cfg.pwd="senha12345678"
wifi.ap.config(cfg)


-- Timer de 1 em 1 segundo para aguardar conectar
local cnt = 0
local maxtry = 30
tmr.alarm(0, 1000, 1, function()
if (wifi.ap.getip() == nil) and (cnt <= maxtry) then
print("Configurando Access Point, aguarde...")
cnt = cnt + 1
else
tmr.stop(0);
print("Configuração efetuada com sucesso")
if (cnt <= maxtry) then
print("Access Point\r\nMAC:"..wifi.ap.getmac().."\r\nIP:"..wifi.ap.getip())
else
print("Timeout configurando access point")
end
cnt = nil;
collectgarbage();
end
end)

-- Configurando Servidor Http
srv=net.createServer(net.TCP)
srv:listen(80,function(conn)
conn:on("receive",function(conn,payload)
print(payload)
conn:send("<h1> Hello, NodeMCU.</h1>")
end)
end)
