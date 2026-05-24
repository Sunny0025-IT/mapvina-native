#pragma once

#include <mlt/layer.hpp>
#include <mlt/util/noncopyable.hpp>

#include <vector>

namespace mlt {

class MapVinaTile : public util::noncopyable {
public:
    MapVinaTile() = delete;
    MapVinaTile(std::vector<Layer> layers_) noexcept
        : layers(std::move(layers_)) {}
    MapVinaTile(MapVinaTile&&) noexcept = default;
    MapVinaTile& operator=(MapVinaTile&&) noexcept = default;

    const std::vector<Layer>& getLayers() const noexcept { return layers; }

    const Layer* getLayer(const std::string_view& name) const noexcept {
        auto hit = std::ranges::find_if(layers, [&](const auto& layer) { return layer.getName() == name; });
        return (hit != layers.end()) ? &*hit : nullptr;
    }

private:
    std::vector<Layer> layers;
};

} // namespace mlt
