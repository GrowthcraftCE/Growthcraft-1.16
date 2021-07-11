import shutil

templateBaseDye = "wort"

dyes = [
  "amber_ale",
  "amber_lager",
  "brown_ale",
  "brown_lager",
  "copper_ale",
  "copper_lager",
  "dark_lager",
  "ipa_ale",
  "old_port_ale",
  "pale_ale",
  "pale_lager",
  "pilsner_lager",
  "stout_ale",
  "vienna_lager"
]

for dye in dyes:
  if dye == templateBaseDye:
    continue
  sourceFiles = [
    "blockstates/%s.json" % templateBaseDye,
    "models/block/%s.json" % templateBaseDye,
    "models/item/%s_bucket.json" % templateBaseDye
  ]
  targetFiles = [
    "blockstates/%s.json" % dye,
    "models/block/%s.json" % dye,
    "models/item/%s_bucket.json" % dye
  ]
  for x in range(0, len(sourceFiles)):
    shutil.copy(sourceFiles[x], targetFiles[x])
    with open(targetFiles[x], 'r') as file:
      contents = file.read()
      contents = contents.replace(templateBaseDye, dye)
    with open(targetFiles[x], 'w') as file:
      file.write(contents)
