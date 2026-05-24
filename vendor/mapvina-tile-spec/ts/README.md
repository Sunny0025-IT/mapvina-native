# mapvina-tile-spec

This package contains a JavaScript decoder for the experimental MapVina Tile (MLT) vector tile format.

## Install

`npm install @mapvina/mapvina-tile-spec`

## Quickstart

To decode a tile, you will want to load `MltDecoder`:

```js
import { decodeTile } from '@mapvina/mapvina-tile-spec';

const data = fs.readFileSync(tilePath);
const tile = decodeTile(data);
```
## Contents

### Code

Code is in `src/`.

### Tests

Tests are in `test/unit/`. Run tests by running `npm run test`.
currently not integrated
