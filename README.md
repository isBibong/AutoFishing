# AutoFishing
Support Minecraft Veriosn: 1.18, 1.19  
Tested Minecraft Versions: 1.18.1

# 插件描述
以此開源為基礎新增開關功能: https://github.com/WMGameLive/AutoFishing

# 所有指令
<ul>
  <li>/autofishing reload - 重新加載 config.yml & playerData.yml。</li>
  <li>/autofishing - 使有 autofishing.use 權限的玩家可以自由開關自動釣魚功能。</li>
</ul>

# 所有權限
<ul>
  <li>autofishing.admin - 允許使用所有指令。(預設: 無)</li>
  <li>autofishing.use - 允許使用 /autofishing 開關自動釣魚功能。(預設: 無)</li>
</ul>

# 設定檔
```yaml
#插件前綴
Prefix: "[AutoFishing]"

CommandMessage:
  Reload: "插件重新加載完成。"
  Enabled_AutoFishing: "成功啟用自動釣魚。"
  Disabled_AutoFishing: "成功關閉自動釣魚。"
  NoPermission: "沒有使用該命令的許可權。"
  NotPlayer: "該指令無法在後台運行。"

AutoFishingSetting:
  #魚咬餌後，在多少個 Tick 內釣起。
  #若超過 25Tick 可能會導致釣魚失敗。
  Ticks_After_Bitten: 5

  #在釣起魚後，間隔多少個 Tick 再次甩竿。
  Ticks_After_Caught: 20
```
