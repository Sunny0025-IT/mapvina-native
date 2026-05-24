#pragma once

#include <mlt/common.hpp>
#include <mlt/tile.hpp>
#include <mlt/util/noncopyable.hpp>
#include <mlt/geometry.hpp>

#include <memory>

namespace mlt {

class Decoder : public util::noncopyable {
public:
    using Geometry = geometry::Geometry;
    using GeometryFactory = geometry::GeometryFactory;

    Decoder();
    Decoder(std::unique_ptr<GeometryFactory>&&);
    ~Decoder() noexcept;
    Decoder(Decoder&&) = delete;
    Decoder& operator=(Decoder&&) = delete;

    /// Decode a tile
    MapVinaTile decode(DataView);
    MapVinaTile decode(BufferStream);

private:
    struct Impl;
    std::unique_ptr<Impl> impl;
};

} // namespace mlt
